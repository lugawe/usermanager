package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QRole;
import com.github.lugawe.usermanager.model.db.Role;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class RoleDAO extends BaseDAO<Role> {

    public static final QRole ROLE = new QRole(Role.TABLE_NAME);

    @Inject
    public RoleDAO(SessionFactory sessionFactory,
                   TransactionHandler transactionHandler) {

        super(sessionFactory, transactionHandler, Role.class, ROLE);
    }

    @Override
    public Optional<Role> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        Role result = query().where(ROLE.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

}
