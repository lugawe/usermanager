package com.github.lugawe.usermanager.server.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.auth.UserAuthenticator;
import com.github.lugawe.usermanager.service.auth.jwt.UserJwtHandler;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class Authenticator implements UserAuthenticator {

    private final UserJwtHandler userJwtHandler;

    @Inject
    public Authenticator(UserJwtHandler userJwtHandler) {
        this.userJwtHandler = Objects.requireNonNull(userJwtHandler);
    }

    @Override
    public Optional<User> authenticate(String token) {
        return Optional.empty();
    }

    @Override
    public boolean available() {
        return true;
    }

    public final UserJwtHandler getUserJwtHandler() {
        return userJwtHandler;
    }

}
