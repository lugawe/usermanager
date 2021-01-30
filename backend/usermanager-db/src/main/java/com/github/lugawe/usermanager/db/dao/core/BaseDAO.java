package com.github.lugawe.usermanager.db.dao.core;

import com.github.lugawe.usermanager.model.db.core.Persistable;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class BaseDAO<T extends Persistable> {

    private static final Logger log = LoggerFactory.getLogger(BaseDAO.class);

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;
    private final EntityPath<T> entityPath;
    private final boolean readOnly;

    public BaseDAO(SessionFactory sessionFactory, Class<T> entityClass, EntityPath<T> entityPath, boolean readOnly) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        this.entityClass = Objects.requireNonNull(entityClass);
        this.entityPath = Objects.requireNonNull(entityPath);
        this.readOnly = readOnly;
    }

    public BaseDAO(SessionFactory sessionFactory, Class<T> entityClass, EntityPath<T> entityPath) {
        this(sessionFactory, entityClass, entityPath, false);
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

    protected HibernateQueryFactory factory() {
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

    public final SessionFactory getSessionFactory() {
        return sessionFactory;
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

}
