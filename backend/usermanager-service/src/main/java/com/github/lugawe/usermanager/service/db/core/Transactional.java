package com.github.lugawe.usermanager.service.db.core;

import com.github.lugawe.usermanager.db.core.BaseDAO;

import java.util.Objects;

public abstract class Transactional<T extends BaseDAO<?>> {

    protected final T baseDAO;

    protected Transactional(T baseDAO) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
    }

}
