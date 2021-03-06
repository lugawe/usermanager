package com.github.lugawe.usermanager.server.model.request;

import javax.validation.constraints.NotEmpty;

public class AuthLoginRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public AuthLoginRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
