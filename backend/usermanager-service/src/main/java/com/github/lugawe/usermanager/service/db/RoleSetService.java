package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.RoleSetDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QRoleSet;
import com.github.lugawe.usermanager.model.db.RoleSet;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.util.Optional;

public class RoleSetService extends BaseService<RoleSetDAO> {

    private static final QRoleSet roleSet = RoleSetDAO.ROLE_SET_PATH;

    @Inject
    public RoleSetService(RoleSetDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Optional<RoleSet> getByName(String name) {
        return inTransaction(() -> {
            RoleSet result = baseDAO.query().where(roleSet.name.eq(name)).fetchFirst();
            return Optional.ofNullable(result);
        });
    }

}
