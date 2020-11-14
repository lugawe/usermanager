package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.RoleSetDAO;
import com.github.lugawe.usermanager.model.db.RoleSet;

import javax.inject.Inject;

public class RoleSetService extends BaseService<RoleSet> {

    @Inject
    public RoleSetService(RoleSetDAO dao) {
        super(dao);
    }

}
