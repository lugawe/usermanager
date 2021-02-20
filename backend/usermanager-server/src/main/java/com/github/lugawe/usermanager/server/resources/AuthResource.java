package com.github.lugawe.usermanager.server.resources;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.server.model.request.AuthLoginRequest;
import com.github.lugawe.usermanager.server.model.request.AuthRegisterRequest;
import com.github.lugawe.usermanager.server.model.response.AuthLoginResponse;
import com.github.lugawe.usermanager.server.model.response.AuthRegisterResponse;
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

    @POST
    @Path("/register")
    public Response register(@Valid AuthRegisterRequest request) {

        Validator<String> name = validationConfig.userNameValidator(request.getName());
        Validator<String> mail = validationConfig.userMailValidator(request.getMail());
        Validator<String> password = validationConfig.userPasswordValidator(request.getPassword());

        User user = authService.register(name, mail, password);
        return new AuthRegisterResponse(true, user.getName()).toResponse();
    }

    @PermitAll
    @GET
    @Path("/check")
    public Response check(@Auth User user) {
        Map<String, String> result = new HashMap<>();
        result.put("id", user.getId().toString());
        result.put("name", user.getName());
        return Response.ok(result).build();
    }

    @POST
    @Path("/login")
    public Response login(@Valid AuthLoginRequest request) {

        Validator<String> name = validationConfig.userNameValidator(request.getName());
        Validator<String> password = validationConfig.userPasswordValidator(request.getPassword());

        Optional<User> user = authService.login(name, password);

        if (user.isPresent()) {
            String accessToken = userJwtHandler.encode(user.get());
            return new AuthLoginResponse(true, null, accessToken).toResponse();
        } else {
            return new AuthLoginResponse(false, null, null).toResponse();
        }
    }

}
