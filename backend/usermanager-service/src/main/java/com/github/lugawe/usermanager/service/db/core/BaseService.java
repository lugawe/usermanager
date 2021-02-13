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
    protected final TransactionHandler transactionHandler;

    public BaseService(T baseDAO, TransactionHandler transactionHandler) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
        this.transactionHandler = Objects.requireNonNull(transactionHandler);
        log.info("construct {} service", baseDAO.getEntityPath());
    }

    @Override
    public <R> R inTransaction(GenericTransaction<R> transaction) {
        return transactionHandler.inTransaction(transaction);
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

    public final TransactionHandler getTransactionHandler() {
        return transactionHandler;
    }

}
