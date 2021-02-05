package com.github.lugawe.usermanager.service.logic;

import com.github.lugawe.usermanager.model.db.Password;
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

    public Optional<User> login(StringValidator name, StringValidator plainPassword) {

        String _name = name.get();
        String _password = plainPassword.get();

        log.info("#login - name: {}", _name);

        try {
            User user = userService.getByName(_name);
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

}
