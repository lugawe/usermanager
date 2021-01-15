package com.github.lugawe.usermanager.service.inject;

import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class ServiceModule extends AbstractModule {

    private final SessionFactory sessionFactory;

    public ServiceModule(SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toInstance(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
