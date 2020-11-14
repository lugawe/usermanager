package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.BaseDAO;
import com.github.lugawe.usermanager.model.db.Persistable;

import java.util.Objects;

public abstract class BaseService<T extends BaseDAO<? extends Persistable>> {

    private final T baseDAO;

    public BaseService(T baseDAO) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

}
