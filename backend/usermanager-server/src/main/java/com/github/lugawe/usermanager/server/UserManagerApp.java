package com.github.lugawe.usermanager.server;

import com.github.lugawe.usermanager.server.resources.InfoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserManagerApp extends Application<UserManagerConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(UserManagerApp.class);

    public static void main(String[] args) throws Exception {
        new UserManagerApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<UserManagerConfiguration> bootstrap) {
        bootstrap.addBundle(new UserManagerHibernateBundle());
    }

    @Override
    public void run(UserManagerConfiguration configuration, Environment environment) throws Exception {
        log.info("init logic");
        registerResources(environment);
    }

    private void registerResources(Environment environment) {
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new InfoResource());
    }

}
