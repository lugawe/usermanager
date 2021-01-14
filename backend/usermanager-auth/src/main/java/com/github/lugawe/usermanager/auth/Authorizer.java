package com.github.lugawe.usermanager.auth;

import java.security.Principal;

public interface Authorizer<T extends Principal> {

    boolean canCheck(T principal);

    boolean hasAccess(T principal, String role);

}
