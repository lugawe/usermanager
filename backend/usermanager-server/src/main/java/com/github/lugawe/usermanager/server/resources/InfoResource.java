package com.github.lugawe.usermanager.server.resources;

import com.github.lugawe.usermanager.service.config.ServiceConfig;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/info")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {

    private final ServiceConfig serviceConfig;

    @Inject
    public InfoResource(ServiceConfig serviceConfig) {
        this.serviceConfig = Objects.requireNonNull(serviceConfig);
    }

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.status(200).entity("pong").build();
    }

}
