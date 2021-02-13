package com.github.lugawe.usermanager.server.model.response;

import javax.ws.rs.core.Response;

public class AuthRegisterResponse implements BaseResponse {

    private boolean success;
    private String name;

    public AuthRegisterResponse() {
    }

    public AuthRegisterResponse(boolean success, String name) {
        this.success = success;
        this.name = name;
    }

    @Override
    public Response toResponse() {
        return Response.status(success ? 200 : 400).entity(this).build();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
