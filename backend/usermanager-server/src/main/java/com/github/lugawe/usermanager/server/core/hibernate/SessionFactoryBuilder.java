package com.github.lugawe.usermanager.server.core.hibernate;

import com.github.lugawe.usermanager.model.db.*;
import com.github.lugawe.usermanager.model.db.base.BaseEntity;
import com.github.lugawe.usermanager.model.db.base.Persistable;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

public class SessionFactoryBuilder implements Provider<SessionFactory> {

    public static final Class<?>[] ENTITY_CLASSES = new Class<?>[]{
            Persistable.class,
            BaseEntity.class,
            Entry.class,
            Password.class,
            Role.class,
            RoleSet.class,
            Token.class,
            User.class
    };

    private final Environment environment;
    private final DataSourceFactory dataSourceFactory;

    public SessionFactoryBuilder(Environment environment, DataSourceFactory dataSourceFactory) {
        this.environment = Objects.requireNonNull(environment);
        this.dataSourceFactory = Objects.requireNonNull(dataSourceFactory);
    }

    @Override
    public SessionFactory get() {
        return build();
    }

    public SessionFactory build() {
        ManagedDataSource dataSource = dataSourceFactory.build(environment.metrics(), "hibernate");
        SessionFactory sessionFactory = buildSessionFactory(dataSource, dataSourceFactory.getProperties());
        environment.lifecycle().manage(buildSessionFactoryManager(dataSource, sessionFactory));
        return sessionFactory;
    }

    protected SessionFactory buildSessionFactory(DataSource dataSource, Map<String, String> properties) {

        BootstrapServiceRegistry bootstrapServiceRegistry = new BootstrapServiceRegistryBuilder().build();

        Configuration configuration = new Configuration(bootstrapServiceRegistry);
        configuration.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "managed");
        configuration.setProperty(AvailableSettings.GENERATE_STATISTICS, "true");
        for (Map.Entry<String, String> property : properties.entrySet()) {
            configuration.setProperty(property.getKey(), property.getValue());
        }
        for (Class<?> entityClass : ENTITY_CLASSES) {
            configuration.addAnnotatedClass(entityClass);
        }

        ConnectionProvider connectionProvider = buildConnectionProvider(dataSource, properties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder(bootstrapServiceRegistry)
                .addService(ConnectionProvider.class, connectionProvider)
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    protected ConnectionProvider buildConnectionProvider(DataSource dataSource, Map<String, String> properties) {
        DatasourceConnectionProviderImpl connectionProvider = new DatasourceConnectionProviderImpl();
        connectionProvider.setDataSource(dataSource);
        connectionProvider.configure(properties);
        return connectionProvider;
    }

    protected Managed buildSessionFactoryManager(ManagedDataSource dataSource, SessionFactory sessionFactory) {
        return new SessionFactoryManager(dataSource, sessionFactory);
    }

}
