package com.github.lugawe.usermanager.service.db.core;

import com.github.lugawe.usermanager.db.core.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseService<T extends BaseDAO<?>> extends Transactional<T> {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    public BaseService(T baseDAO) {
        super(baseDAO);
        log.debug("construct {} service", baseDAO.getEntityPath());
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

}
