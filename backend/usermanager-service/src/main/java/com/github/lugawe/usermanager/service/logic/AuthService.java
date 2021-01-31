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

    public Optional<User> login(StringValidator name, StringValidator password) {

        String _name = name.get();
        String _password = password.get();

        log.info("#login - name: {}", _name);

        User user = userService.getByName(_name);
        if (user != null) {
            Password pwd = user.getPassword();
            if (pwd != null) {
                if (BCrypt.checkpw(_password, pwd.getHash())) {
                    passwordService.updateLastAccess(pwd.getId());
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }

}
