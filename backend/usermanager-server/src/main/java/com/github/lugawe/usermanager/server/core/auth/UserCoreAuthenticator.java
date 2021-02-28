package com.github.lugawe.usermanager.server.core.auth;

import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.service.auth.UserAuthenticator;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class UserCoreAuthenticator implements Authenticator<String, User> {

    private static final Logger log = LoggerFactory.getLogger(UserCoreAuthenticator.class);

    private final UserAuthenticator authenticator;

    @Inject
    public UserCoreAuthenticator(UserAuthenticator authenticator) {
        this.authenticator = Objects.requireNonNull(authenticator);
    }

    @Override
    public Optional<User> authenticate(String token) throws AuthenticationException {
        if (!authenticator.available()) {
            throw new AuthenticationException("user authenticator not available");
        }
        Optional<User> principal = authenticator.authenticate(token);
        if (!principal.isPresent()) {
            return Optional.empty();
        }
        return check(principal.get());
    }

    protected Optional<User> check(User user) {
        if (user.isLocked()) {
            return Optional.empty();
        }
        log.info("user authenticated: {} ({})", user.getName(), user.getId());
        return Optional.of(user);
    }

}
