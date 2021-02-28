package com.github.lugawe.usermanager.db.transaction.impl;

import com.github.lugawe.usermanager.db.transaction.Transaction;
import com.github.lugawe.usermanager.db.transaction.TransactionException;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import java.util.Objects;

public class DefaultTransactionHandler implements TransactionHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultTransactionHandler.class);

    private final Provider<SessionFactory> sessionFactoryProvider;

    public DefaultTransactionHandler(Provider<SessionFactory> sessionFactoryProvider) {
        this.sessionFactoryProvider = Objects.requireNonNull(sessionFactoryProvider);
    }

    public <T> T inTransaction(Transaction<T> transaction) {
        if (transaction == null) {
            throw new NullPointerException("param transaction is null");
        }
        SessionFactory sessionFactory = sessionFactoryProvider.get();
        if (sessionFactory == null) {
            throw new NullPointerException("provided session factory is null");
        }
        org.hibernate.Transaction txn = null;
        Session session = null;
        try {
            log.debug("transaction started");
            session = sessionFactory.openSession();
            ManagedSessionContext.bind(session);
            txn = session.beginTransaction();
            T result = transaction.execute(session);
            commit(txn);
            return result;
        } catch (Exception ex) {
            rollback(txn);
            throw new TransactionException(ex, this);
        } finally {
            if (session != null) {
                session.close();
            }
            ManagedSessionContext.unbind(sessionFactory);
            log.debug("transaction completed");
        }
    }

    protected void commit(org.hibernate.Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    protected void rollback(org.hibernate.Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }

    public final Provider<SessionFactory> getSessionFactoryProvider() {
        return sessionFactoryProvider;
    }

}
