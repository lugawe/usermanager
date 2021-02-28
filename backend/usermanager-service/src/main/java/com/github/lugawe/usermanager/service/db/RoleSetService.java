package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.RoleSetDAO;
import com.github.lugawe.usermanager.model.db.auth.QRoleSet;
import com.github.lugawe.usermanager.model.db.auth.RoleSet;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleSetService extends BaseService<RoleSetDAO> {

    private static final QRoleSet roleSet = RoleSetDAO.ROLE_SET;

    @Inject
    public RoleSetService(RoleSetDAO dao) {
        super(dao);
    }

    public RoleSet getByName(String name) {
        return inTransaction((s) -> baseDAO.query().where(roleSet.name.eq(name)).fetchFirst());
    }

}
