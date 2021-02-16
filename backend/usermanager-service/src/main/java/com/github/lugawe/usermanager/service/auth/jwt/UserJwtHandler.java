package com.github.lugawe.usermanager.service.auth.jwt;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.EntryService;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class UserJwtHandler {

    private final JwtHandler jwtHandler;
    private final EntryService entryService;

    @Inject
    public UserJwtHandler(JwtHandler jwtHandler, EntryService entryService) {
        this.jwtHandler = Objects.requireNonNull(jwtHandler);
        this.entryService = Objects.requireNonNull(entryService);
    }

    public Optional<User> getUser(String token) {
        if (token == null || token.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public final JwtHandler getJwtHandler() {
        return jwtHandler;
    }

    public final EntryService getEntryService() {
        return entryService;
    }

}
