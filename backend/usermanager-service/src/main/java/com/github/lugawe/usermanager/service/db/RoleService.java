package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.RoleDAO;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleService extends BaseService<RoleDAO> {

    @Inject
    public RoleService(RoleDAO dao) {
        super(dao);
    }

}
