package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.Password;
import com.github.lugawe.usermanager.model.db.query.QPassword;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class PasswordDAO extends BaseDAO<Password> {

    private final QPassword password = QPassword.password;

    @Inject
    public PasswordDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Password.class);
    }

    @Override
    public final EntityPath<Password> getEntityPath() {
        return password;
    }

}