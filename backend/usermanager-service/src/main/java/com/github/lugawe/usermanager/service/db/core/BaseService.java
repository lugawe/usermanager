package com.github.lugawe.usermanager.service.db.core;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.service.interfaces.CheckedProvider;
import com.github.lugawe.usermanager.service.interfaces.CheckedRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public <R> R inTransaction(CheckedProvider<R> transaction) {
        return transactionHandler.inTransaction(transaction);
    }

    public void inTransaction(CheckedRunnable transaction) {
        transactionHandler.inTransaction(transaction);
    }

    public final T getBaseDAO() {
        return baseDAO;
    }

    public final TransactionHandler getTransactionHandler() {
        return transactionHandler;
    }

}
