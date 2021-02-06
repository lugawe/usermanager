package com.github.lugawe.usermanager.service.config;

import com.auth0.jwt.algorithms.Algorithm;

import java.time.Duration;

public class JwtConfig {

    private String secret = "super_secret_123456";

    private String issuer = "usermanager";
    private Duration lifetime = Duration.ofDays(365);

    public JwtConfig() {
    }

    public Algorithm buildAlgorithm() {
        return Algorithm.HMAC256(getSecret());
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

    public Duration getLifetime() {
        return lifetime;
    }

    public void setLifetime(Duration lifetime) {
        this.lifetime = lifetime;
    }

}
