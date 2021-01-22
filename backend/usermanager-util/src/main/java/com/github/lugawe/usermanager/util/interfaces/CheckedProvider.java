package com.github.lugawe.usermanager.util.interfaces;

@FunctionalInterface
public interface CheckedProvider<T> {

    T get() throws Exception;

}
