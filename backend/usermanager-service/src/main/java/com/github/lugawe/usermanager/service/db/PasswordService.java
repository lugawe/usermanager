package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.PasswordDAO;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class PasswordService extends BaseService<PasswordDAO> {

    @Inject
    public PasswordService(PasswordDAO dao) {
        super(dao);
    }

}
