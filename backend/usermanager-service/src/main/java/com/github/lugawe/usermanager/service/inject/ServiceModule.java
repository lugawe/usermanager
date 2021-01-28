package com.github.lugawe.usermanager.service.inject;

import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class ServiceModule extends AbstractModule {

    private final ServiceConfig serviceConfig;
    private final SessionFactory sessionFactory;

    public ServiceModule(ServiceConfig serviceConfig, SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
        this.serviceConfig = Objects.requireNonNull(serviceConfig);
    }

    @Override
    protected void configure() {
        bind(ServiceConfig.class).toInstance(serviceConfig);
        bind(SessionFactory.class).toInstance(sessionFactory);
    }

    public final ServiceConfig getServiceConfig() {
        return serviceConfig;
    }

    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
