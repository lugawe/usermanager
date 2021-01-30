package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.dao.TokenDAO;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.QToken;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class TokenService extends BaseService<TokenDAO> {

    private final QToken token = QToken.token;

    @Inject
    public TokenService(TokenDAO dao, TransactionHandler handler) {
        super(dao, handler);
    }

}
