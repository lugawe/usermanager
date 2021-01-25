package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.db.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.RoleSet;
import com.github.lugawe.usermanager.model.db.query.QRoleSet;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class RoleSetDAO extends BaseDAO<RoleSet> {

    private final QRoleSet roleSet = QRoleSet.roleSet;

    @Inject
    public RoleSetDAO(SessionFactory sessionFactory) {
        super(sessionFactory, RoleSet.class);
    }

    @Override
    public EntityPath<RoleSet> getEntityPath() {
        return roleSet;
    }

}
