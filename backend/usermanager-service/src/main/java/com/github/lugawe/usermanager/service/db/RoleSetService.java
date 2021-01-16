package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.RoleSetDAO;
import com.github.lugawe.usermanager.service.db.core.BaseService;

import javax.inject.Inject;

public class RoleSetService extends BaseService<RoleSetDAO> {

    @Inject
    public RoleSetService(RoleSetDAO dao) {
        super(dao);
    }

}
