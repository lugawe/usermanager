package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.UserDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QUser;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.util.Optional;

public class UserService extends BaseService<UserDAO> {

    private static final QUser user = UserDAO.USER;

    @Inject
    public UserService(UserDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Optional<User> getByName(String name) {
        return inTransaction(() -> {
            User result = baseDAO.query().where(user.name.eq(name)).fetchFirst();
            return Optional.ofNullable(result);
        });
    }

}
