package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.TokenDAO;
import com.github.lugawe.usermanager.model.db.Token;

import javax.inject.Inject;

public class TokenService extends BaseService<Token> {

    @Inject
    public TokenService(TokenDAO dao) {
        super(dao);
    }

}
