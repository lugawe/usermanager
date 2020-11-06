package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.Role;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class RoleDAO extends BaseDAO<Role> {

    @Inject
    public RoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

}
