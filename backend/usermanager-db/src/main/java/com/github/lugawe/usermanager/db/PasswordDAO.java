package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.Password;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class PasswordDAO extends BaseDAO<Password> {

    @Inject
    public PasswordDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Password.class);
    }

}
