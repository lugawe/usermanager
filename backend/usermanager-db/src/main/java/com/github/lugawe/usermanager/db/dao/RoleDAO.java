package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.Role;
import com.github.lugawe.usermanager.model.db.query.QRole;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class RoleDAO extends BaseDAO<Role> {

    private final QRole role = QRole.role;

    @Inject
    public RoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    @Override
    public final EntityPath<Role> getEntityPath() {
        return role;
    }

}
