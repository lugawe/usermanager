package com.github.lugawe.usermanager.service.db.core;

import com.github.lugawe.usermanager.db.core.BaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import java.util.Objects;

public abstract class BaseService<T extends BaseDAO<?>> {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    private final T baseDAO;
    private final TransactionHandler transactionHandler;

    public BaseService(T baseDAO, TransactionHandler transactionHandler) {
        this.baseDAO = Objects.requireNonNull(baseDAO);
        this.transactionHandler = Objects.requireNonNull(transactionHandler);
        log.debug("construct {} service", baseDAO.getEntityPath());
    }

    public BaseService(T baseDAO) {
        this(baseDAO, new TransactionHandler(baseDAO::getSessionFactory));
    }

    public <R> R inTransaction(Provider<R> transaction) {
        return transactionHandler.inTransaction(transaction);
    }

    public void inTransaction(Runnable transaction) {
        transactionHandler.inTransaction(transaction);
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

    public final TransactionHandler getTransactionHandler() {
        return transactionHandler;
    }

}
