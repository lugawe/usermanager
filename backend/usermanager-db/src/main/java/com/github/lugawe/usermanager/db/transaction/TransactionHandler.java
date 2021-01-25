package com.github.lugawe.usermanager.db.transaction;

public interface TransactionHandler {

    public <T> T inTransaction(Transaction<T> transaction);

    public default void inTransaction(VoidTransaction transaction) {
        if (transaction == null) {
            throw new NullPointerException("param transaction is null");
        }
        inTransaction(() -> {
            transaction.run();
            return null;
        });
    }

}
