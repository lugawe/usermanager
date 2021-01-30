package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.Password;
import com.github.lugawe.usermanager.model.db.QPassword;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class PasswordDAO extends BaseDAO<Password> {

    public static final QPassword PASSWORD_PATH = QPassword.password;

    @Inject
    public PasswordDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Password.class, PASSWORD_PATH);
    }

    @Override
    public Optional<Password> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        Password result = query().where(PASSWORD_PATH.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

}
