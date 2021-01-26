package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.RoleSet;
import com.github.lugawe.usermanager.model.db.query.QRoleSet;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class RoleSetDAO extends BaseDAO<RoleSet> {

    private final QRoleSet roleSet = QRoleSet.roleSet;

    @Inject
    public RoleSetDAO(SessionFactory sessionFactory) {
        super(sessionFactory, RoleSet.class);
    }

    @Override
    public Optional<RoleSet> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        RoleSet result = query().where(roleSet.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public final EntityPath<RoleSet> getEntityPath() {
        return roleSet;
    }

}
