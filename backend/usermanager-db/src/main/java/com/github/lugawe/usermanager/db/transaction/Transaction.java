package com.github.lugawe.usermanager.db.transaction;

@FunctionalInterface
public interface Transaction<T> {

    T run() throws Exception;

}
