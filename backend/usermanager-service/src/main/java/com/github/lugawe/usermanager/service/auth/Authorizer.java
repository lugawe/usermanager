package com.github.lugawe.usermanager.service.auth;

import com.github.lugawe.usermanager.model.db.User;

public interface Authorizer {

    boolean hasAccess(User user, String role);

}
