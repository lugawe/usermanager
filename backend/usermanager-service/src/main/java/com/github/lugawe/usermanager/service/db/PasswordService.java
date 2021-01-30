package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.PasswordDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QPassword;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class PasswordService extends BaseService<PasswordDAO> {

    private static final QPassword password = PasswordDAO.PASSWORD_PATH;

    @Inject
    public PasswordService(PasswordDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

}
