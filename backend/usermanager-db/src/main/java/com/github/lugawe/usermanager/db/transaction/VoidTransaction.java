package com.github.lugawe.usermanager.db.transaction;

@FunctionalInterface
public interface VoidTransaction {

    void run() throws Exception;

}
