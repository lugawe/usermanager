package com.github.lugawe.usermanager.util.interfaces;

@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Exception;

}
