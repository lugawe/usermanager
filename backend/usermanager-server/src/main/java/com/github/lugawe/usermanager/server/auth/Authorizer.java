package com.github.lugawe.usermanager.server.auth;

import com.github.lugawe.usermanager.model.db.auth.Role;
import com.github.lugawe.usermanager.model.db.auth.RoleSet;
import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.service.auth.UserAuthorizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Set;

public class Authorizer implements UserAuthorizer {

    private static final Logger log = LoggerFactory.getLogger(Authorizer.class);

    @Inject
    public Authorizer() {
    }

    @Override
    public boolean hasAccess(User user, String role) {
        if (user == null || role == null || role.isEmpty()) {
            return false;
        }
        log.info("check user {} ({}) access, required role {}", user.getId(), user.getType(), role);
        if (User.Type.ADMIN.equals(user.getType())) {
            return true;
        }
        return check(user.getRoleSet(), role);
    }

    protected boolean check(RoleSet roleSet, String role) {
        if (roleSet == null || roleSet.isLocked()) {
            return false;
        }
        Set<Role> roles = roleSet.getRoles();
        if (roles == null || roles.size() < 1) {
            return false;
        }
        return roles.stream().anyMatch(r -> r != null && !r.isLocked() && r.getName().equals(role));
    }

}
