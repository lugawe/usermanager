package com.github.lugawe.usermanager.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

public class RawUser implements Principal, Serializable {

    private String id;
    private String name;
    private String type;
    private Set<String> roles;

    private Map<String, Object> custom;

    public RawUser() {
    }

    public RawUser(String id, String name, String type, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Map<String, Object> getCustom() {
        return custom;
    }

    public void setCustom(Map<String, Object> custom) {
        this.custom = custom;
    }

}
