package com.github.lugawe.usermanager.service;

import com.github.lugawe.usermanager.db.BaseDAO;

import java.util.Objects;

public abstract class BaseService<T extends BaseDAO<?>> {

    private final T baseDAO;

    public BaseService(T baseDAO) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

}
