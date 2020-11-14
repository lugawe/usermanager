package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.RoleSetDAO;

import javax.inject.Inject;

public class RoleSetService extends BaseService<RoleSetDAO> {

    @Inject
    public RoleSetService(RoleSetDAO dao) {
        super(dao);
    }

}
