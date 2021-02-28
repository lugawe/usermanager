package com.github.lugawe.usermanager.server.resources;

import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.server.core.auth.AuthRequestFilter;
import com.github.lugawe.usermanager.server.model.request.AuthLoginRequest;
import com.github.lugawe.usermanager.server.model.request.AuthRegisterRequest;
import com.github.lugawe.usermanager.service.auth.jwt.UserJwtHandler;
import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.github.lugawe.usermanager.service.config.ValidationConfig;
import com.github.lugawe.usermanager.service.logic.AuthService;
import com.github.lugawe.usermanager.service.validation.Validator;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final ValidationConfig validationConfig;
    private final AuthService authService;
    private final UserJwtHandler userJwtHandler;

    @Inject
    public AuthResource(ServiceConfig config, AuthService authService, UserJwtHandler userJwtHandler) {
        this.validationConfig = config.getValidationConfig();
        this.authService = authService;
        this.userJwtHandler = userJwtHandler;
    }

    protected Map<String, String> user(User user) {
        Map<String, String> result = new HashMap<>();
        result.put("id", user.getId().toString());
        result.put("name", user.getName());
        return result;
    }

    protected NewCookie accessTokenCookie(String token) {
        return AuthRequestFilter.createAccessTokenCookie(token);
    }

    @POST
    @Path("/register")
    public Response register(@Valid AuthRegisterRequest request) {

        Validator<String> name = validationConfig.userNameValidator(request.getName());
        Validator<String> mail = validationConfig.userMailValidator(request.getMail());
        Validator<String> password = validationConfig.userPasswordValidator(request.getPassword());

        User user = authService.register(name, mail, password);
        if (user == null) {
            throw new WebApplicationException(Response.status(400).build());
        }

        return Response.ok(user.getId().toString()).build();
    }

    @PermitAll
    @GET
    @Path("/check")
    public Response check(@Auth User user) {
        return Response.ok(user(user)).build();
    }

    @GET
    @Path("/logout")
    public Response logout() {
        NewCookie cookie = accessTokenCookie("");
        return Response.ok().cookie(cookie).build();
    }

    @POST
    @Path("/login")
    public Response login(@Valid AuthLoginRequest request) {

        Validator<String> name = validationConfig.userNameValidator(request.getName());
        Validator<String> password = validationConfig.userPasswordValidator(request.getPassword());

        Optional<User> user = authService.login(name, password);

        if (!user.isPresent()) {
            throw new WebApplicationException(Response.status(401).build());
        }

        User authenticatedUser = user.get();

        String accessToken = userJwtHandler.encode(authenticatedUser);
        NewCookie cookie = accessTokenCookie(accessToken);
        return Response.ok(user(authenticatedUser)).cookie(cookie).build();
    }

}
