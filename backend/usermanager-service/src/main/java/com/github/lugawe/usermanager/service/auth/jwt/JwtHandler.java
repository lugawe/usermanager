package com.github.lugawe.usermanager.service.auth.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.github.lugawe.usermanager.service.config.JwtConfig;

import javax.inject.Inject;
import java.util.Objects;

public class JwtHandler {

    private final JwtConfig jwtConfig;
    private final Algorithm algorithm;

    @Inject
    public JwtHandler(JwtConfig jwtConfig) {
        this.jwtConfig = Objects.requireNonNull(jwtConfig);
        this.algorithm = this.jwtConfig.buildAlgorithm();
    }

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

}
