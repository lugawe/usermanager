package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.RoleSet;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class RoleSetDAO extends BaseDAO<RoleSet> {

    @Inject
    public RoleSetDAO(SessionFactory sessionFactory) {
        super(sessionFactory, RoleSet.class);
    }

}
