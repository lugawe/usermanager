package com.github.lugawe.usermanager.db.transaction;

public interface TransactionHandler {

    <T> T inTransaction(Transaction<T> transaction) throws TransactionException;

}
