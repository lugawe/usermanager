package com.github.lugawe.usermanager.service.interfaces;

@FunctionalInterface
public interface CheckedProvider<T> {

    T get() throws Exception;

}
