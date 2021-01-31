package com.github.lugawe.usermanager.service.logic;

import com.github.lugawe.usermanager.service.db.PasswordService;
import com.github.lugawe.usermanager.service.db.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class RegisterService {

    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);

    private final UserService userService;
    private final PasswordService passwordService;

    @Inject
    public RegisterService(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

}
