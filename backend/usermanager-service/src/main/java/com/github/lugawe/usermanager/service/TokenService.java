package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.TokenDAO;

import javax.inject.Inject;

public class TokenService extends BaseService<TokenDAO> {

    @Inject
    public TokenService(TokenDAO dao) {
        super(dao);
    }

}
