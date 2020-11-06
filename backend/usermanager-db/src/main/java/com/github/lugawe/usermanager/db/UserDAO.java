package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.QUser;
import com.github.lugawe.usermanager.model.db.User;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class UserDAO extends BaseDAO<User> {

    private final QUser user = QUser.user;

    @Inject
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public EntityPath<User> getEntityPath() {
        return user;
    }

}
