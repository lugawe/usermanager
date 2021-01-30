package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.RoleDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QRole;
import com.github.lugawe.usermanager.model.db.Role;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.util.Optional;

public class RoleService extends BaseService<RoleDAO> {

    private static final QRole role = RoleDAO.ROLE_PATH;

    @Inject
    public RoleService(RoleDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Optional<Role> getByName(String name) {
        return inTransaction(() -> {
            Role result = baseDAO.query().where(role.name.eq(name)).fetchFirst();
            return Optional.ofNullable(result);
        });
    }

}
