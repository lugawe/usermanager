package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.TokenDAO;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class TokenService extends BaseService<TokenDAO> {

    @Inject
    public TokenService(TokenDAO dao) {
        super(dao);
    }

}
