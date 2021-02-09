package com.github.lugawe.usermanager.server;

import com.github.lugawe.usermanager.server.core.CoreApp;
import com.github.lugawe.usermanager.server.resources.AuthResource;
import com.github.lugawe.usermanager.server.resources.InfoResource;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserManagerApp extends CoreApp {

    private static final Logger log = LoggerFactory.getLogger(UserManagerApp.class);

    public static void main(String[] args) throws Exception {
        new UserManagerApp().run(args);
    }

    @Override
    public void run(UserManagerConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
        registerResources(environment);
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(injector.getInstance(AuthResource.class));
        environment.jersey().register(injector.getInstance(InfoResource.class));
    }

}
