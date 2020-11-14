package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.UserDAO;
import com.github.lugawe.usermanager.model.db.User;

import javax.inject.Inject;

public class UserService extends BaseService<User> {

    @Inject
    public UserService(UserDAO dao) {
        super(dao);
    }

}
