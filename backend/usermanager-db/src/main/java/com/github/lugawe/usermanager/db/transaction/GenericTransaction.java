package com.github.lugawe.usermanager.db.transaction;

@FunctionalInterface
public interface GenericTransaction<T> {

    T run() throws Exception;

}
