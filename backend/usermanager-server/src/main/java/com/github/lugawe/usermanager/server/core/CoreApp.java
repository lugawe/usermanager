package com.github.lugawe.usermanager.server.core;

import com.github.lugawe.usermanager.server.UserManagerConfiguration;
import com.github.lugawe.usermanager.server.core.mapper.ValidationExceptionMapper;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
        init(configuration, environment);
        initJersey(environment);
    }

    private void init(UserManagerConfiguration configuration, Environment environment) {
        injector = new CoreInjector(configuration.getServiceConfig(), coreHibernateBundle.getSessionFactory()).buildInjector();
    }

    private void initJersey(Environment environment) {
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(injector.getInstance(ValidationExceptionMapper.class));
    }

}
