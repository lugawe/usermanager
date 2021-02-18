package com.github.lugawe.usermanager.server.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.auth.UserAuthenticator;
import com.github.lugawe.usermanager.service.auth.jwt.UserJwtHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class Authenticator implements UserAuthenticator {

    private static final Logger log = LoggerFactory.getLogger(Authenticator.class);

    private final UserJwtHandler userJwtHandler;

    @Inject
    public Authenticator(UserJwtHandler userJwtHandler) {
        this.userJwtHandler = Objects.requireNonNull(userJwtHandler);
    }

    @Override
    public Optional<User> authenticate(String token) {
        return userJwtHandler.decode(token);
    }

    @Override
    public boolean available() {
        return true;
    }

    public final UserJwtHandler getUserJwtHandler() {
        return userJwtHandler;
    }

}
