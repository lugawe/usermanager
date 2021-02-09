package com.github.lugawe.usermanager.server.core;

import com.github.lugawe.usermanager.server.UserManagerConfiguration;
import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CoreApp extends Application<UserManagerConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(CoreApp.class);

    private final CoreHibernateBundle coreHibernateBundle = new CoreHibernateBundle();

    protected Injector injector;

    protected CoreApp() {
    }

    @Override
    public void initialize(Bootstrap<UserManagerConfiguration> bootstrap) {
        bootstrap.addBundle(coreHibernateBundle);
    }

    @Override
    public void run(UserManagerConfiguration configuration, Environment environment) throws Exception {
        log.info("init core logic");
        registerInjector(configuration);
    }

    private void registerInjector(UserManagerConfiguration configuration) {
        ServiceConfig serviceConfig = configuration.getServiceConfig();
        SessionFactory sessionFactory = coreHibernateBundle.getSessionFactory();
        injector = new CoreInjector(serviceConfig, sessionFactory).buildInjector();
    }

}
