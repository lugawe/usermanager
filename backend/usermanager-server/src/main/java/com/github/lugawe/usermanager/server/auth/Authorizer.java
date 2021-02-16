package com.github.lugawe.usermanager.server.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.auth.UserAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class Authorizer implements UserAuthorizer {

    private static final Logger log = LoggerFactory.getLogger(Authorizer.class);

    @Inject
    public Authorizer() {
    }

    @Override
    public boolean hasAccess(User user, String role) {
        return false;
    }

}
