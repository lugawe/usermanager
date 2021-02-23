package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QRoleSet;
import com.github.lugawe.usermanager.model.db.RoleSet;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class RoleSetDAO extends BaseDAO<RoleSet> {

    public static final QRoleSet ROLE_SET = new QRoleSet(RoleSet.TABLE_NAME);

    @Inject
    public RoleSetDAO(SessionFactory sessionFactory,
                      TransactionHandler transactionHandler) {

        super(sessionFactory, transactionHandler, RoleSet.class, ROLE_SET);
    }

    @Override
    public Optional<RoleSet> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        RoleSet result = query().where(ROLE_SET.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

}
