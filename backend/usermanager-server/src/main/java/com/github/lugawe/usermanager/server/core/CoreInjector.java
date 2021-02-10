package com.github.lugawe.usermanager.server.core;

import com.github.lugawe.usermanager.db.transaction.TransactionHandler;
import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.github.lugawe.usermanager.service.inject.ServiceModule;
import com.github.lugawe.usermanager.service.logic.transaction.DefaultTransactionHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class CoreInjector {

    private final ServiceConfig serviceConfig;
    private final SessionFactory sessionFactory;

    public CoreInjector(ServiceConfig serviceConfig, SessionFactory sessionFactory) {
        this.serviceConfig = Objects.requireNonNull(serviceConfig);
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    protected TransactionHandler buildTransactionHandler() {
        return new DefaultTransactionHandler(() -> sessionFactory);
    }

    protected ServiceModule buildServiceModule() {
        return new ServiceModule(serviceConfig, sessionFactory, buildTransactionHandler());
    }

    protected Injector buildInjector() {
        return Guice.createInjector(buildServiceModule());
    }

}