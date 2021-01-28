package com.github.lugawe.usermanager.service.basetest;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class H2TestDatabase {

    private H2TestDatabase() {
    }

    /**
     * Builds h2 in-memory database SessionFactory
     *
     * @return SessionFactory object
     * @see org.h2.Driver
     */
    public static SessionFactory buildSessionFactory() {

        Reflections reflections = new Reflections("com.github.lugawe.usermanager.model.db");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);

        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "org.h2.Driver");
        settings.put("hibernate.connection.url", "jdbc:h2:mem:test");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "password");
        settings.put("dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("hibernate.current_session_context_class", "managed");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        for (Class<?> eClass : classes) {
            metadataSources.addAnnotatedClass(eClass);
        }

        return metadataSources.buildMetadata().getSessionFactoryBuilder().build();
    }

}
