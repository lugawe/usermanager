package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.TokenDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QToken;
import com.github.lugawe.usermanager.model.db.Token;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;
import java.util.Optional;

public class TokenService extends BaseService<TokenDAO> {

    private static final QToken token = TokenDAO.TOKEN_PATH;

    @Inject
    public TokenService(TokenDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

    public Optional<Token> getByTypeAndValue(Token.Type type, String value) {
        return inTransaction(() -> {
            Token result = baseDAO.query().where(token.type.eq(type).and(token.value.eq(value))).fetchFirst();
            return Optional.ofNullable(result);
        });
    }

}
