package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.PasswordDAO;

import javax.inject.Inject;

public class PasswordService extends BaseService<PasswordDAO> {

    @Inject
    public PasswordService(PasswordDAO dao) {
        super(dao);
    }

}
