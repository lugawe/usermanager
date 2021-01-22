package com.github.lugawe.usermanager.service.db.core;

import com.github.lugawe.usermanager.util.interfaces.CheckedProvider;
import com.github.lugawe.usermanager.util.interfaces.CheckedRunnable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;

import javax.inject.Provider;
import java.util.Objects;

public class TransactionHandler {

    private final Provider<SessionFactory> sessionFactoryProvider;

    public TransactionHandler(Provider<SessionFactory> sessionFactoryProvider) {
        this.sessionFactoryProvider = Objects.requireNonNull(sessionFactoryProvider);
    }

    public <T> T inTransaction(CheckedProvider<T> transaction) {
        if (transaction == null) {
            throw new NullPointerException("param transaction is null");
        }
        SessionFactory sessionFactory = sessionFactoryProvider.get();
        if (sessionFactory == null) {
            throw new NullPointerException("provided session factory is null");
        }
        Transaction txn = null;
        try (Session session = sessionFactory.openSession()) {
            ManagedSessionContext.bind(session);
            txn = session.beginTransaction();
            T result = transaction.get();
            commit(txn);
            return result;
        } catch (Exception ex) {
            rollback(txn);
            throw new TransactionException(ex);
        } finally {
            ManagedSessionContext.unbind(sessionFactory);
        }
    }

    public void inTransaction(CheckedRunnable transaction) {
        if (transaction == null) {
            throw new NullPointerException("param transaction is null");
        }
        inTransaction(() -> {
            transaction.run();
            return null;
        });
    }

    private void commit(Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.commit();
        }
    }

    private void rollback(Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }

}
