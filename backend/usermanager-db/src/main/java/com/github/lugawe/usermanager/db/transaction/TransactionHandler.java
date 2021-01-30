package com.github.lugawe.usermanager.db.transaction;

public interface TransactionHandler {

    public <T> T inTransaction(GenericTransaction<T> transaction) throws TransactionException;

    public default void inTransaction(VoidTransaction transaction) throws TransactionException {
        if (transaction == null) {
            throw new NullPointerException("param transaction is null");
        }
        inTransaction(() -> {
            transaction.run();
            return null;
        });
    }

}
