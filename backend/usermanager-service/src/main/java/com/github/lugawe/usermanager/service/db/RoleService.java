package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.RoleDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleService extends BaseService<RoleDAO> {

    @Inject
    public RoleService(RoleDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

}
