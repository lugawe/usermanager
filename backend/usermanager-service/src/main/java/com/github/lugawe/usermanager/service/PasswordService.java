package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.PasswordDAO;
import com.github.lugawe.usermanager.model.db.Password;

import javax.inject.Inject;

public class PasswordService extends BaseService<Password> {

    @Inject
    public PasswordService(PasswordDAO dao) {
        super(dao);
    }

}
