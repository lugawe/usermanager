package com.github.lugawe.usermanager.auth;

import java.io.Serializable;
import java.util.Objects;

public class JWTClaim<T> implements Serializable {

    private final String name;
    private final T value;

    public JWTClaim(String name, T value) {
        this.name = Objects.requireNonNull(name);
        this.value = Objects.requireNonNull(value);
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public static <T> JWTClaim<T> of(String name, T value) {
        return new JWTClaim<>(name, value);
    }

}
