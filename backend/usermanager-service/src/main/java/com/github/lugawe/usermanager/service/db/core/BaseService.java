package com.github.lugawe.usermanager.service.db.core;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.db.transaction.GenericTransaction;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public abstract class BaseService<T extends BaseDAO<?>> implements TransactionHandler {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected final T baseDAO;

    public BaseService(T baseDAO) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
        log.info("construct {} service", baseDAO.getEntityPath());
    }

    @Override
    public <R> R inTransaction(GenericTransaction<R> transaction) {
        return baseDAO.inTransaction(transaction);
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

}
