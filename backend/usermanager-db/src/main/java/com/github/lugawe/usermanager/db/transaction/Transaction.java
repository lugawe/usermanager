package com.github.lugawe.usermanager.db.transaction;

import org.hibernate.Session;

@FunctionalInterface
public interface Transaction<T> {

    T execute(Session session) throws Exception;

}
