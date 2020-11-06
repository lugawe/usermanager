package com.github.lugawe.usermanager.db;

import com.github.lugawe.usermanager.model.db.Persistable;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.hibernate.HibernateDeleteClause;
import com.querydsl.jpa.hibernate.HibernateInsertClause;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateUpdateClause;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseDAO<T extends Persistable> {

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public BaseDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        this.entityClass = Objects.requireNonNull(entityClass);
    }

    public HibernateQuery<T> query(final long offset, final long limit) {
        HibernateQuery<T> query = new HibernateQuery<>(getCurrentSession());
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
        return new HibernateInsertClause(getCurrentSession(), getEntityPath());
    }

    public UUID insertEntity(T entity) {
        if (entity == null) {
            throw new NullPointerException();
        }
        return (UUID) getCurrentSession().save(entity);
    }

    public HibernateUpdateClause update() {
        return new HibernateUpdateClause(getCurrentSession(), getEntityPath());
    }

    public HibernateDeleteClause delete() {
        return new HibernateDeleteClause(getCurrentSession(), getEntityPath());
    }

    public Optional<T> get(UUID id) {
        if (id == null) {
            throw new NullPointerException();
        }
        return Optional.ofNullable(getCurrentSession().get(getEntityClass(), id));
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public abstract EntityPath<T> getEntityPath();

}
