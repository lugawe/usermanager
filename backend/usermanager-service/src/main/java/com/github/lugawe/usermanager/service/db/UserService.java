package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.UserDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.Password;
import com.github.lugawe.usermanager.model.db.QUser;
import com.github.lugawe.usermanager.model.db.RoleSet;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class UserService extends BaseService<UserDAO> {

    private static final QUser user = UserDAO.USER;

    @Inject
    public UserService(UserDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public User getByName(String name) {
        return inTransaction(() -> baseDAO.query().where(user.name.eq(name)).fetchFirst());
    }

    public User create(String name, Password password, RoleSet roleSet) {

        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setName(name);
        user.setType(User.Type.USER);
        user.setPassword(password);
        user.setRoleSet(roleSet);
        user.setCreatedAt(now);

        return inTransaction(() -> baseDAO.get(baseDAO.insert(user)));
    }

}
