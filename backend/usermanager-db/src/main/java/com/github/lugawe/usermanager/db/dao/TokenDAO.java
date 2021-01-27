package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.QToken;
import com.github.lugawe.usermanager.model.db.Token;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class TokenDAO extends BaseDAO<Token> {

    private final QToken token = QToken.token;

    @Inject
    public TokenDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Token.class);
    }

    @Override
    public Optional<Token> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        Token result = query().where(token.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public final EntityPath<Token> getEntityPath() {
        return token;
    }

}
