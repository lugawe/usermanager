package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.RoleSetDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleSetService extends BaseService<RoleSetDAO> {

    @Inject
    public RoleSetService(RoleSetDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

}
