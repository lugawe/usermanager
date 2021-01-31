package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.RoleDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QRole;
import com.github.lugawe.usermanager.model.db.Role;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleService extends BaseService<RoleDAO> {

    private static final QRole role = RoleDAO.ROLE;

    @Inject
    public RoleService(RoleDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Role getByName(String name) {
        return inTransaction(() -> baseDAO.query().where(role.name.eq(name)).fetchFirst());
    }

}
