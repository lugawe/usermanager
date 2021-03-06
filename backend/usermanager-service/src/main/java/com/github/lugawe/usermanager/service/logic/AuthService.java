package com.github.lugawe.usermanager.service.logic;

import com.github.lugawe.usermanager.model.db.auth.Password;
import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.service.db.PasswordService;
import com.github.lugawe.usermanager.service.db.UserService;
import com.github.lugawe.usermanager.service.validation.Validator;
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

    public User register(Validator<String> name, Validator<String> mail, Validator<String> plainPassword) {

        String _name = name.get();
        String _mail = mail.get();
        String _password = plainPassword.get();

        Password password = passwordService.create(_password);
        User user = userService.create(_name, password, null);

        log.info("#register - user registered - id: {}, name: {}", user.getId(), user.getName());

        return user;
    }

    public Optional<User> login(Validator<String> name, Validator<String> plainPassword) {

        String _name = name.get();
        String _password = plainPassword.get();

        log.info("#login - name: {}", _name);

        try {
            User user = userService.getByName(_name);
            if (user != null) {
                Password password = user.getPassword();
                if (password != null) {
                    passwordService.updateLastAccess(password.getId());
                    if (BCrypt.checkpw(_password, password.getHash())) {
                        return check(user, password);
                    }
                }
            }
        } catch (Exception ex) {
            log.warn("#login", ex);
        }

        return Optional.empty();
    }

    protected Optional<User> check(User user, Password password) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(password);
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

}
