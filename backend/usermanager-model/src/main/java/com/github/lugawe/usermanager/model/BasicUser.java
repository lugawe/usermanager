package com.github.lugawe.usermanager.model;

import com.github.lugawe.usermanager.model.db.User;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

public class BasicUser implements Principal, Serializable {

    private String name;
    private User.Type type;
    private Set<String> roles;

    public BasicUser() {
    }

    public BasicUser(String name, User.Type type, Set<String> roles) {
        this.name = name;
        this.type = type;
        this.roles = roles;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User.Type getType() {
        return type;
    }

    public void setType(User.Type type) {
        this.type = type;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
