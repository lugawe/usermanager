package com.github.lugawe.usermanager.service.auth;

import com.github.lugawe.usermanager.model.db.auth.User;

import java.util.Optional;

public interface UserAuthenticator {

    Optional<User> authenticate(String token);

    boolean available();

}
