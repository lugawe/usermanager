package com.github.lugawe.usermanager.server.core;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.server.UserManagerConfiguration;
import com.github.lugawe.usermanager.server.core.auth.AuthRequestFilter;
import com.github.lugawe.usermanager.server.core.hibernate.SessionFactoryBuilder;
import com.github.lugawe.usermanager.server.core.mapper.ValidationExceptionMapper;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CoreApp extends Application<UserManagerConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(CoreApp.class);

    protected SessionFactory sessionFactory;
    protected Injector injector;

    protected CoreApp() {
    }

    @Override
    public void initialize(Bootstrap<UserManagerConfiguration> bootstrap) {
    }

    @Override
    public void run(UserManagerConfiguration configuration, Environment environment) throws Exception {
        log.info("init core");
        init(configuration, environment);
        initAuth(environment);
        initLogic(environment);
    }

    private void init(UserManagerConfiguration configuration, Environment environment) {
        sessionFactory = new SessionFactoryBuilder(configuration.getDatabase(), environment).build();
        injector = new CoreInjector(configuration.getServiceConfig(), sessionFactory).buildInjector(new CoreModule());
    }

    private void initAuth(Environment environment) {
        log.info("init auth");
        AuthRequestFilter authRequestFilter = injector.getInstance(AuthRequestFilter.Builder.class).buildAuthFilter();
        environment.jersey().register(new AuthDynamicFeature(authRequestFilter));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    private void initLogic(Environment environment) {
        log.info("init logic");
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(injector.getInstance(ValidationExceptionMapper.class));
    }

}
