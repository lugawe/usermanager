package com.github.lugawe.usermanager.db.transaction;

public class TransactionException extends RuntimeException {

    private TransactionHandler transactionHandler;

    public TransactionException() {
        super();
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(String message, Throwable cause, TransactionHandler handler) {
        this(message, cause);
        this.transactionHandler = handler;
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }

    public TransactionException(Throwable cause, TransactionHandler handler) {
        this(cause);
        this.transactionHandler = handler;
    }

    public TransactionHandler getTransactionHandler() {
        return transactionHandler;
    }

    public void setTransactionHandler(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

}
