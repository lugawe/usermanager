package com.github.lugawe.usermanager.server.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.auth.UserAuthorizer;

public class Authorizer implements UserAuthorizer {

    @Override
    public boolean hasAccess(User user, String role) {
        return false;
    }

}
