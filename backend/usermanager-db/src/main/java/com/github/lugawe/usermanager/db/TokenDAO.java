package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.QToken;
import com.github.lugawe.usermanager.model.db.Token;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TokenDAO extends BaseDAO<Token> {

    private final QToken token = QToken.token;

    @Inject
    public TokenDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Token.class);
    }

    @Override
    public EntityPath<Token> getEntityPath() {
        return token;
    }

}
