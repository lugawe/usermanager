package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QToken;
import com.github.lugawe.usermanager.model.db.Token;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class TokenDAO extends BaseDAO<Token> {

    public static final QToken TOKEN = new QToken(Token.TABLE_NAME);

    @Inject
    public TokenDAO(SessionFactory sessionFactory,
                    TransactionHandler transactionHandler) {

        super(sessionFactory, transactionHandler, Token.class, TOKEN);
    }

    @Override
    public Optional<Token> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        Token result = query().where(TOKEN.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

}
