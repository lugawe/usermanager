package com.github.lugawe.usermanager.service.inject;

import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.apache.commons.lang3.ArrayUtils;
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

    public static Injector createInjector(ServiceModule module, Module... modules) {
        if (module == null) {
            throw new NullPointerException("param module is null");
        }
        return Guice.createInjector(ArrayUtils.add(modules, module));
    }

}
