package com.github.lugawe.usermanager.util;

import java.util.Optional;

public final class Result<T> {

    private final boolean success;
    private final T value;
    private final Throwable error;

    private Result(boolean success, T value, Throwable error) {
        this.success = success;
        this.value = value;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        return value;
    }

    public Throwable getError() {
        return error;
    }

    public Optional<T> asOptional() {
        if (isSuccess()) {
            return Optional.of(getValue());
        }
        return Optional.empty();
    }

    public static <T> Result<T> success(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return new Result<>(true, value, null);
    }

    public static <T> Result<T> failure(Throwable error) {
        if (error == null) {
            throw new NullPointerException();
        }
        return new Result<>(false, null, error);
    }

}
