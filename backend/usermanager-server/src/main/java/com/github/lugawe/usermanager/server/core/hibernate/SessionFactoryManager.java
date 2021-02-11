package com.github.lugawe.usermanager.server.core.hibernate;

import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.lifecycle.Managed;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class SessionFactoryManager implements Managed {

    private final ManagedDataSource managedDataSource;
    private final SessionFactory sessionFactory;

    public SessionFactoryManager(ManagedDataSource managedDataSource, SessionFactory sessionFactory) {
        this.managedDataSource = Objects.requireNonNull(managedDataSource);
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    @Override
    public void start() throws Exception {
        managedDataSource.start();
    }

    @Override
    public void stop() throws Exception {
        sessionFactory.close();
        managedDataSource.stop();
    }

}
