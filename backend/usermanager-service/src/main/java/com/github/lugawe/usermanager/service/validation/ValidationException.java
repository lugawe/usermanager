package com.github.lugawe.usermanager.service.validation;

public class ValidationException extends RuntimeException {

    private Validator<?> validator;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public Validator<?> getValidator() {
        return validator;
    }

    public void setValidator(Validator<?> validator) {
        this.validator = validator;
    }

}
