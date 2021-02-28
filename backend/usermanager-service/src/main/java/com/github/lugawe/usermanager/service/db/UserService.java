package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.auth.UserDAO;
import com.github.lugawe.usermanager.model.db.auth.Password;
import com.github.lugawe.usermanager.model.db.auth.QUser;
import com.github.lugawe.usermanager.model.db.auth.RoleSet;
import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserService extends BaseService<UserDAO> {

    private static final QUser user = UserDAO.USER;

    @Inject
    public UserService(UserDAO dao) {
        super(dao);
    }

    public User getById(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        return inTransaction((s) -> baseDAO.query().where(user.id.eq(id)).fetchFirst());
    }

    public User getByName(String name) {
        if (name == null) {
            throw new NullPointerException("param name is null");
        }
        return inTransaction((s) -> baseDAO.query().where(user.name.eq(name)).fetchFirst());
    }

    public User create(String name, Password password, RoleSet roleSet) {

        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setName(name);
        user.setType(User.Type.BASIC_USER);
        user.setPassword(password);
        user.setRoleSet(roleSet);
        user.setCreatedAt(now);

        return inTransaction((s) -> baseDAO.get(baseDAO.insert(user)));
    }

}
