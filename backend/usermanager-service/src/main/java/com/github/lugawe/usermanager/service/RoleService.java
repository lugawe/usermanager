package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.RoleDAO;
import com.github.lugawe.usermanager.model.db.Role;

import javax.inject.Inject;

public class RoleService extends BaseService<Role> {

    @Inject
    public RoleService(RoleDAO dao) {
        super(dao);
    }

}
