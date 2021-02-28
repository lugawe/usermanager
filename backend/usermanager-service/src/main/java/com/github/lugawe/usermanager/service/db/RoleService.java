package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.auth.RoleDAO;
import com.github.lugawe.usermanager.model.db.auth.QRole;
import com.github.lugawe.usermanager.model.db.auth.Role;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleService extends BaseService<RoleDAO> {

    private static final QRole role = RoleDAO.ROLE;

    @Inject
    public RoleService(RoleDAO dao) {
        super(dao);
    }

    public Role getByName(String name) {
        return inTransaction((s) -> baseDAO.query().where(role.name.eq(name)).fetchFirst());
    }

}
