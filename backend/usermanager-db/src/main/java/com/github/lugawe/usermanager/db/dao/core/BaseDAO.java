package com.github.lugawe.usermanager.db.dao.core;

import com.github.lugawe.usermanager.db.transaction.Transaction;
import com.github.lugawe.usermanager.db.transaction.TransactionException;
import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.model.db.core.Persistable;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class BaseDAO<T extends Persistable> implements TransactionHandler {

    private static final Logger log = LoggerFactory.getLogger(BaseDAO.class);

    private final SessionFactory sessionFactory;
    private final TransactionHandler transactionHandler;
    private final Class<T> entityClass;
    private final EntityPath<T> entityPath;

    private boolean readOnly;

    public BaseDAO(SessionFactory sessionFactory,
                   TransactionHandler transactionHandler,
                   Class<T> entityClass,
                   EntityPath<T> entityPath,
                   boolean readOnly) {

        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        this.transactionHandler = Objects.requireNonNull(transactionHandler);
        this.entityClass = Objects.requireNonNull(entityClass);
        this.entityPath = Objects.requireNonNull(entityPath);
        this.readOnly = readOnly;
    }

    public BaseDAO(SessionFactory sessionFactory,
                   TransactionHandler transactionHandler,
                   Class<T> entityClass,
                   EntityPath<T> entityPath) {

        this(sessionFactory, transactionHandler, entityClass, entityPath, false);
    }

    protected Session configureSession(Session session) {
        if (session == null) {
            throw new NullPointerException("param session is null");
        }
        session.setDefaultReadOnly(readOnly);
        return session;
    }

    protected Session session() {
        return configureSession(sessionFactory.getCurrentSession());
    }

    public HibernateQueryFactory factory() {
        log.debug("create new hibernate query factory");
        return new HibernateQueryFactory(this::session);
    }

    public HibernateQuery<T> query(long offset, long limit) {
        HibernateQuery<T> query = factory().selectFrom(entityPath);
        if (offset > 0) {
            query = query.offset(offset);
        }
        if (limit > 0) {
            query = query.limit(limit);
        }
        return query;
    }

    public HibernateQuery<T> query() {
        return query(0, 0);
    }

    public HibernateInsertClause insert() {
        if (readOnly) {
            throw new IllegalStateException("cannot create insert clause: dao is read only");
        }
        return factory().insert(entityPath);
    }

    public UUID insert(T entity) {
        if (entity == null) {
            throw new NullPointerException("param entity is null");
        }
        if (readOnly) {
            throw new IllegalStateException("cannot insert entity: dao is read only");
        }
        return (UUID) session().save(entity);
    }

    public HibernateUpdateClause update() {
        if (readOnly) {
            throw new IllegalStateException("cannot create update clause: dao is read only");
        }
        return factory().update(entityPath);
    }

    public HibernateDeleteClause delete() {
        if (readOnly) {
            throw new IllegalStateException("cannot create delete clause: dao is read only");
        }
        return factory().delete(entityPath);
    }

    public abstract Optional<T> tryGet(UUID id);

    public T get(UUID id) {
        return tryGet(id).orElseThrow(() -> new NullPointerException("entity not found"));
    }

    public List<T> fetchAll() {
        return Collections.unmodifiableList(query().fetch());
    }

    @Override
    public <R> R inTransaction(Transaction<R> transaction) throws TransactionException {
        return transactionHandler.inTransaction(transaction);
    }

    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public final TransactionHandler getTransactionHandler() {
        return transactionHandler;
    }

    public final Class<T> getEntityClass() {
        return entityClass;
    }

    public final EntityPath<T> getEntityPath() {
        return entityPath;
    }

    public final boolean isReadOnly() {
        return readOnly;
    }

    public final void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

}
