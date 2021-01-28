package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.UserDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class UserService extends BaseService<UserDAO> {

    @Inject
    public UserService(UserDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

}
