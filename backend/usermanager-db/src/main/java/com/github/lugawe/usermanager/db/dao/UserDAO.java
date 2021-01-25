package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.model.db.query.QUser;
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
    public final EntityPath<User> getEntityPath() {
        return user;
    }

}
