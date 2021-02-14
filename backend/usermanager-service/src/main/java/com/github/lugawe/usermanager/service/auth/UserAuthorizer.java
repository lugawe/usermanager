package com.github.lugawe.usermanager.service.auth;

import com.github.lugawe.usermanager.model.db.User;

public interface UserAuthorizer {

    boolean hasAccess(User user, String role);

}
