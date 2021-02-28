package com.github.lugawe.usermanager.db.dao.auth;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.auth.QUser;
import com.github.lugawe.usermanager.model.db.auth.User;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserDAO extends BaseDAO<User> {

    public static final QUser USER = new QUser(User.TABLE_NAME);

    @Inject
    public UserDAO(SessionFactory sessionFactory,
                   TransactionHandler transactionHandler) {

        super(sessionFactory, transactionHandler, User.class, USER);
    }

    @Override
    public Optional<User> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        User result = query().where(USER.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

}
