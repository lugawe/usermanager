package com.github.lugawe.usermanager.service.logic;

import com.github.lugawe.usermanager.model.db.Password;
import com.github.lugawe.usermanager.model.db.Role;
import com.github.lugawe.usermanager.model.db.RoleSet;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.PasswordService;
import com.github.lugawe.usermanager.service.db.UserService;
import com.github.lugawe.usermanager.service.validation.StringValidator;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserService userService;
    private final PasswordService passwordService;

    @Inject
    public AuthService(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    protected boolean checkPassword(String plainPassword, Password password) {
        plainPassword = Objects.requireNonNull(plainPassword);
        passwordService.updateLastAccess(password.getId());
        return BCrypt.checkpw(plainPassword, password.getHash());
    }

    protected Optional<User> check(User user, Password password) {
        if (user.isLocked()) {
            log.warn("#checkUser: user {} is locked", user.getId());
            return Optional.empty();
        }
        if (password.isLocked()) {
            log.warn("#checkUser: password {} is locked", password.getId());
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public Optional<User> login(StringValidator userName, StringValidator plainPassword) {

        String _userName = userName.get();
        String _password = plainPassword.get();

        log.info("#login - userName: {}", _userName);

        try {
            User user = userService.getByName(_userName);
            if (user != null) {
                Password password = user.getPassword();
                if (password != null) {
                    if (checkPassword(_password, password)) {
                        return check(user, password);
                    }
                }
            }
        } catch (Exception ex) {
            log.warn("#login", ex);
        }

        return Optional.empty();
    }

    public boolean isUserInRole(User user, String role) {

        if (user == null) {
            throw new NullPointerException("param user is null");
        }
        if (role == null) {
            throw new NullPointerException("param role is null");
        }

        log.info("#isUserInRole - userName: {}, roleName: {}", user.getName(), role);

        try {
            if (!user.isLocked()) {
                if (user.getType().equals(User.Type.ADMIN)) {
                    return true;
                }
                RoleSet roleSet = user.getRoleSet();
                if (roleSet != null && !roleSet.isLocked()) {
                    Set<Role> roles = roleSet.getRoles();
                    if (roles != null && roles.size() > 0) {
                        return roles.stream().anyMatch(r -> r.getName().equals(role));
                    }
                }
            }
        } catch (Exception ex) {
            log.warn("#isUserInRole", ex);
        }

        return false;
    }

}
