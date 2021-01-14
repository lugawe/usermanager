package com.github.lugawe.usermanager.auth;

import java.security.Principal;
import java.util.Optional;

public interface Authenticator<T extends Principal> {

    Optional<T> get(String token);

    boolean available();

}
