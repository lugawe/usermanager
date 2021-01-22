package com.github.lugawe.usermanager.service.interfaces;

@FunctionalInterface
public interface CheckedRunnable {

    void run() throws Exception;

}
