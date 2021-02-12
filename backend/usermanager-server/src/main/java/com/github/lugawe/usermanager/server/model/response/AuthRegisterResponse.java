package com.github.lugawe.usermanager.server.model.response;

public class AuthRegisterResponse {

    private boolean success;
    private String name;

    public AuthRegisterResponse() {
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
