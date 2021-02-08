package com.github.lugawe.usermanager.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserManagerApp extends Application<UserManagerConfiguration> {

    private static final Logger log = LoggerFactory.getLogger(UserManagerApp.class);

    private static UserManagerApp app;

    public static void main(String[] args) throws Exception {
        log.info("start");
        app = new UserManagerApp();
        app.run(args);
    }

    @Override
    public void run(UserManagerConfiguration configuration, Environment environment) throws Exception {
    }

}
