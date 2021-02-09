package com.github.lugawe.usermanager.server.resources;

import com.github.lugawe.usermanager.server.resources.request.AuthLoginRequest;
import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.github.lugawe.usermanager.service.config.ValidationConfig;
import com.github.lugawe.usermanager.service.logic.AuthService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("/login")
    public Response login(@Valid AuthLoginRequest request) {
        return Response.ok().build();
    }

}
