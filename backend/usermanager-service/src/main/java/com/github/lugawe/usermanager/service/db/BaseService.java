package com.github.lugawe.usermanager.service.db;

import com.github.lugawe.usermanager.db.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public abstract class BaseService<T extends BaseDAO<?>> {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected final T baseDAO;

    public BaseService(T baseDAO) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
        log.debug("construct {} service", baseDAO.getEntityPath());
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

}
