package com.github.lugawe.usermanager.server.model.request;

import javax.validation.constraints.NotEmpty;

public class AuthRegisterRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String mail;

    @NotEmpty
    private String password;

    public AuthRegisterRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
