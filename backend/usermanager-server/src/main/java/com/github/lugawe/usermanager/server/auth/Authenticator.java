package com.github.lugawe.usermanager.server.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.auth.UserAuthenticator;

import java.util.Optional;

public class Authenticator implements UserAuthenticator {

    @Override
    public Optional<User> authenticate(String token) {
        return Optional.empty();
    }

    @Override
    public boolean available() {
        return true;
    }

}
