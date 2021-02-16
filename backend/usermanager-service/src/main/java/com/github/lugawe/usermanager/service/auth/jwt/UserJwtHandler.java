package com.github.lugawe.usermanager.service.auth.jwt;

import javax.inject.Inject;
import java.util.Objects;

public class UserJwtHandler {

    private final JwtHandler jwtHandler;

    @Inject
    public UserJwtHandler(JwtHandler jwtHandler) {
        this.jwtHandler = Objects.requireNonNull(jwtHandler);
    }

    public final JwtHandler getJwtHandler() {
        return jwtHandler;
    }

}
