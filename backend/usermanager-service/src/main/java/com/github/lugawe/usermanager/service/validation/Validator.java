package com.github.lugawe.usermanager.service.validation;

public interface Validator<T> {

    T get() throws ValidationException;

    default boolean isValid() {
        return true;
    }

    default String type() {
        return null;
    }

    default String pattern() {
        return null;
    }

    default String target() {
        return null;
    }

}
