package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.User;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class UserDAO extends BaseDAO<User> {

    @Inject
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

}
