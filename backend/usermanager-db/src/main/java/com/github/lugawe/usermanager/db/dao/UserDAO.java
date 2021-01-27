package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.QUser;
import com.github.lugawe.usermanager.model.db.User;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserDAO extends BaseDAO<User> {

    private final QUser user = QUser.user;

    @Inject
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public Optional<User> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        User result = query().where(user.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public final EntityPath<User> getEntityPath() {
        return user;
    }

}
