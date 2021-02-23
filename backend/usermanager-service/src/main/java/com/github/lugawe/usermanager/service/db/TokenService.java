package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.TokenDAO;
import com.github.lugawe.usermanager.model.db.QToken;
import com.github.lugawe.usermanager.model.db.Token;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class TokenService extends BaseService<TokenDAO> {

    private static final QToken token = TokenDAO.TOKEN;

    @Inject
    public TokenService(TokenDAO dao) {
        super(dao);
    }

    public Token getByTypeAndValue(Token.Type type, String value) {
        return inTransaction(() -> baseDAO.query().where(token.type.eq(type).and(token.value.eq(value))).fetchFirst());
    }

}
