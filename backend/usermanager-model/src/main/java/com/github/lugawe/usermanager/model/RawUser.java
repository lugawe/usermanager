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
    private Map<String, String> entries;

    public RawUser() {
    }

    public RawUser(String id, String name, String type, Set<String> roles, Map<String, String> entries) {
        this();
        this.id = id;
        this.name = name;
        this.type = type;
        this.roles = roles;
        this.entries = entries;
    }

    public RawUser(String id, String name, String type, Set<String> roles) {
        this(id, name, type, roles, null);
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

    public Map<String, String> getEntries() {
        return entries;
    }

    public void setEntries(Map<String, String> entries) {
        this.entries = entries;
    }

}
