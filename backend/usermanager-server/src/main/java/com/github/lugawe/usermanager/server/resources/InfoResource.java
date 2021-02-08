package com.github.lugawe.usermanager.server.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/info")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InfoResource {

    private static final Logger log = LoggerFactory.getLogger(InfoResource.class);

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.status(200).entity("pong").build();
    }

}
