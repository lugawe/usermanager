package com.github.lugawe.usermanager.auth;

import org.joda.time.Duration;

public class JWTConfig {

    private String issuer = "lugawe.usermanager";

    private Duration lifetime = Duration.standardHours(1);

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
