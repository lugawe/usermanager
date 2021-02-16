package com.github.lugawe.usermanager.server;

import com.github.lugawe.usermanager.server.auth.Authenticator;
import com.github.lugawe.usermanager.server.auth.Authorizer;
import com.github.lugawe.usermanager.service.auth.UserAuthenticator;
import com.github.lugawe.usermanager.service.auth.UserAuthorizer;
import com.google.inject.AbstractModule;

import javax.inject.Inject;
import java.util.Objects;

public class UserManagerModule extends AbstractModule {

    private final Authenticator authenticator;
    private final Authorizer authorizer;

    @Inject
    public UserManagerModule(Authenticator authenticator, Authorizer authorizer) {
        this.authenticator = Objects.requireNonNull(authenticator);
        this.authorizer = Objects.requireNonNull(authorizer);
    }

    @Override
    protected void configure() {
        bind(UserAuthenticator.class).toInstance(authenticator);
        bind(UserAuthorizer.class).toInstance(authorizer);
    }

}
