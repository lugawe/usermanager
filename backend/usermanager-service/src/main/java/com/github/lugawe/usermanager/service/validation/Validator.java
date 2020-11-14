package com.github.lugawe.usermanager.service.validation;

public interface Validator<T> {

    T get() throws ValidationException;

    default boolean isValid() {
        return true;
    }

}
