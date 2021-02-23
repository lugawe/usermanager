package com.github.lugawe.usermanager.service.test;

import com.github.lugawe.usermanager.db.transaction.impl.DefaultTransactionHandler;
import com.github.lugawe.usermanager.service.config.ServiceConfig;
import com.github.lugawe.usermanager.service.inject.ServiceModule;
import com.google.inject.Injector;
import org.hibernate.SessionFactory;

public final class BaseTestHelper {

    private BaseTestHelper() {
    }

    public static ServiceModule defaultServiceModule() {
        SessionFactory factory = H2TestDatabase.buildSessionFactory();
        return new ServiceModule(new ServiceConfig(), factory, new DefaultTransactionHandler(() -> factory));
    }

    public static Injector defaultInjector() {
        return ServiceModule.createInjector(defaultServiceModule());
    }

}
