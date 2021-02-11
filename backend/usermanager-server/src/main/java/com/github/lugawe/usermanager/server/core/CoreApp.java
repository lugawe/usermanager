package com.github.lugawe.usermanager.server.core;

import com.github.lugawe.usermanager.server.UserManagerConfiguration;
import com.github.lugawe.usermanager.server.core.hibernate.SessionFactoryBuilder;
import com.github.lugawe.usermanager.server.core.mapper.ValidationExceptionMapper;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CoreApp extends Application<UserManagerConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(CoreApp.class);

    protected SessionFactoryBuilder sessionFactoryBuilder;
    protected Injector injector;

    protected CoreApp() {
    }

    @Override
    public void initialize(Bootstrap<UserManagerConfiguration> bootstrap) {
    }

    @Override
    public void run(UserManagerConfiguration configuration, Environment environment) throws Exception {
        log.info("init core logic");
        init(configuration, environment);
        initJersey(environment);
    }

    private void init(UserManagerConfiguration configuration, Environment environment) {
        sessionFactoryBuilder = new SessionFactoryBuilder(configuration.getDatabase(), environment);
        injector = new CoreInjector(configuration.getServiceConfig(), sessionFactoryBuilder.build()).buildInjector();
    }

    private void initJersey(Environment environment) {
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(injector.getInstance(ValidationExceptionMapper.class));
    }

}
