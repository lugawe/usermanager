package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.RoleDAO;

import javax.inject.Inject;

public class RoleService extends BaseService<RoleDAO> {

    @Inject
    public RoleService(RoleDAO dao) {
        super(dao);
    }

}
