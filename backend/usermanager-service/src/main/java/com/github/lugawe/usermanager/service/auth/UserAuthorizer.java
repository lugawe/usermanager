package com.github.lugawe.usermanager.service.auth;

import com.github.lugawe.usermanager.model.db.auth.User;

public interface UserAuthorizer {

    boolean hasAccess(User user, String role);

}
