package com.github.lugawe.usermanager.service.auth.jwt;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.UserService;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserJwtHandler {

    public static final String USER_ID_CLAIM = "userId";

    private final JwtHandler jwtHandler;
    private final UserService userService;

    @Inject
    public UserJwtHandler(JwtHandler jwtHandler, UserService userService) {
        this.jwtHandler = Objects.requireNonNull(jwtHandler);
        this.userService = Objects.requireNonNull(userService);
    }

    public Optional<User> getUser(String token) {
        if (token == null || token.trim().isEmpty()) {
            return Optional.empty();
        }
        Optional<String> id = jwtHandler.getClaimValue(token, USER_ID_CLAIM);
        if (!id.isPresent()) {
            return Optional.empty();
        }
        String userId = id.get();
        return Optional.ofNullable(userService.getById(UUID.fromString(userId)));
    }

    public final JwtHandler getJwtHandler() {
        return jwtHandler;
    }

    public final UserService getUserService() {
        return userService;
    }

}
