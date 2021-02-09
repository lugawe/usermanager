package com.github.lugawe.usermanager.server.resources.request;

import javax.validation.constraints.NotEmpty;

public class AuthLoginRequest {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String plainPassword;

    public AuthLoginRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

}
