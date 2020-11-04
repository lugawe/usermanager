package com.github.lugawe.usermanager.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

public class User implements Principal, Serializable {

    private String id;
    private String displayName;
    private Set<String> roles;
    private Map<String, String> data;

    public User() {
    }

    @Override
    public String getName() {
        return String.format("%s-%s", getDisplayName(), getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return getName();
    }

}
