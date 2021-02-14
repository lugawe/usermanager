package com.github.lugawe.usermanager.service.auth.jwt;

import java.io.Serializable;

public interface JwtClaim extends Serializable {

    String getKey();

    String getValue();

    static JwtClaim of(String key, String value) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("param key is null or empty");
        }
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("param value is null or empty");
        }
        return new JwtClaim() {

            @Override
            public String getKey() {
                return key;
            }

            @Override
            public String getValue() {
                return value;
            }

        };
    }

}
