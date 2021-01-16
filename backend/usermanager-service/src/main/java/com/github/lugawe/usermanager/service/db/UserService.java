package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.UserDAO;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class UserService extends BaseService<UserDAO> {

    @Inject
    public UserService(UserDAO dao) {
        super(dao);
    }

}
