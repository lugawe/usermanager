package com.github.lugawe.usermanager.server.model.request;

import javax.validation.constraints.NotEmpty;

public class AuthRegisterRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    public AuthRegisterRequest() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
