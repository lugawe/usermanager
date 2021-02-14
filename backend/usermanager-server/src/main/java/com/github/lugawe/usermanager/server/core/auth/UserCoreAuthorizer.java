package com.github.lugawe.usermanager.server.core.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.auth.UserAuthorizer;
import io.dropwizard.auth.Authorizer;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import java.util.Objects;

public class UserCoreAuthorizer implements Authorizer<User> {

    private final UserAuthorizer authorizer;

    @Inject
    public UserCoreAuthorizer(UserAuthorizer authorizer) {
        this.authorizer = Objects.requireNonNull(authorizer);
    }

    @Override
    public boolean authorize(User user, String role, @Nullable ContainerRequestContext context) {
        return authorizer.hasAccess(user, role);
    }

    @Override
    public boolean authorize(User user, String role) {
        return authorize(user, role, null);
    }

}
