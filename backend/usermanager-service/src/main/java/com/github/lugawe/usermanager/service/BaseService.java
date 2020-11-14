package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.BaseDAO;
import com.github.lugawe.usermanager.model.db.Persistable;

import java.util.Objects;

public abstract class BaseService<T extends Persistable> {

    private final BaseDAO<T> baseDAO;

    public BaseService(BaseDAO<T> baseDAO) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
    }

    public BaseDAO<T> getBaseDAO() {
        return baseDAO;
    }

}
