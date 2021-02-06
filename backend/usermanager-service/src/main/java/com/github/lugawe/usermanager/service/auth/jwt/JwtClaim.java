package com.github.lugawe.usermanager.service.auth.jwt;

import java.io.Serializable;

public class JwtClaim implements Serializable {

    private String key;
    private String value;

    public JwtClaim() {
    }

    public JwtClaim(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
