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

    public BaseDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        this.entityClass = Objects.requireNonNull(entityClass);
    }

    public HibernateQueryFactory factory() {
        log.debug("create new hibernate query factory");
        return new HibernateQueryFactory(this::getCurrentSession);
    }

    public HibernateQuery<T> query(long offset, long limit) {
        HibernateQuery<T> query = factory().selectFrom(getEntityPath());
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
        return factory().insert(getEntityPath());
    }

    public HibernateUpdateClause update() {
        return factory().update(getEntityPath());
    }

    public HibernateDeleteClause delete() {
        return factory().delete(getEntityPath());
    }

    public abstract Optional<T> tryGet(UUID id);

    public T get(UUID id) {
        return tryGet(id).orElseThrow(() -> new NullPointerException("entity not found"));
    }

    public List<T> fetchAll() {
        return Collections.unmodifiableList(query().fetch());
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public final Class<T> getEntityClass() {
        return entityClass;
    }

    public abstract EntityPath<T> getEntityPath();

}
