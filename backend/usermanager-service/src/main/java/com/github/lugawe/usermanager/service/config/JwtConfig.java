package com.github.lugawe.usermanager.service.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class JwtConfig implements Serializable {

    @JsonIgnore
    private transient Algorithm algorithm;

    private String secret = "super_secret_123456";

    private String issuer = "usermanager";
    private int lifetime = 60 * 24 * 365;

    public JwtConfig() {
    }

    public JwtConfig(Algorithm algorithm) {
        this.algorithm = Objects.requireNonNull(algorithm);
    }

    public Algorithm buildAlgorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC256(getSecret());
        }
        return algorithm;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

}
