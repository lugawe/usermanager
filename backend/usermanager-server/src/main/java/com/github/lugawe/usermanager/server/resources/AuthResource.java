package com.github.lugawe.usermanager.server.resources;

import com.github.lugawe.usermanager.server.model.request.AuthLoginRequest;
import com.github.lugawe.usermanager.server.model.request.AuthRegisterRequest;
import com.github.lugawe.usermanager.server.model.response.AuthLoginResponse;
import com.github.lugawe.usermanager.server.model.response.AuthRegisterResponse;
import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.github.lugawe.usermanager.service.config.ValidationConfig;
import com.github.lugawe.usermanager.service.logic.AuthService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final ValidationConfig validationConfig;
    private final AuthService authService;

    @Inject
    public AuthResource(ServiceConfig config, AuthService authService) {
        this.validationConfig = config.getValidationConfig();
        this.authService = authService;
    }

    @POST
    @Path("/register")
    public AuthRegisterResponse register(@Valid AuthRegisterRequest request) {
        return null;
    }

    @POST
    @Path("/login")
    public AuthLoginResponse login(@Valid AuthLoginRequest request) {
        return null;
    }

}
