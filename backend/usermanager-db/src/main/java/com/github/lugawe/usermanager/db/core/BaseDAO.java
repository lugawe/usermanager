package com.github.lugawe.usermanager.db.core;

import com.github.lugawe.usermanager.model.db.core.Persistable;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;

public abstract class BaseDAO<T extends Persistable> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public BaseDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        this.entityClass = Objects.requireNonNull(entityClass);
    }

    public HibernateQueryFactory queryFactory() {
        return new HibernateQueryFactory(getCurrentSession());
    }

    public HibernateQuery<T> query(long offset, long limit) {
        HibernateQuery<T> query = queryFactory().selectFrom(getEntityPath());
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
        return queryFactory().insert(getEntityPath());
    }

    public HibernateUpdateClause update() {
        return queryFactory().update(getEntityPath());
    }

    public HibernateDeleteClause delete() {
        return queryFactory().delete(getEntityPath());
    }

    public UUID insert(T entity) {
        if (entity == null) {
            throw new NullPointerException("param entity is null");
        }
        return (UUID) getCurrentSession().save(entity);
    }

    public Optional<T> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        return Optional.ofNullable(getCurrentSession().get(entityClass, id));
    }

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
