package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.Token;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TokenDAO extends BaseDAO<Token> {

    @Inject
    public TokenDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Token.class);
    }

}
