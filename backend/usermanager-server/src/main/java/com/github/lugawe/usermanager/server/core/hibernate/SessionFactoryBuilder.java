package com.github.lugawe.usermanager.server.core.hibernate;

import com.github.lugawe.usermanager.model.db.auth.Password;
import com.github.lugawe.usermanager.model.db.auth.Role;
import com.github.lugawe.usermanager.model.db.auth.RoleSet;
import com.github.lugawe.usermanager.model.db.auth.User;
import com.github.lugawe.usermanager.model.db.core.EntityCore;
import com.github.lugawe.usermanager.model.db.core.Persistable;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

public class SessionFactoryBuilder implements Provider<SessionFactory> {

    private static final Logger log = LoggerFactory.getLogger(SessionFactoryBuilder.class);

    public static final Class<?>[] CORE_ENTITY_CLASSES = new Class<?>[]{
            Persistable.class,
            EntityCore.class,
            Password.class,
            Role.class,
            RoleSet.class,
            User.class
    };

    private final DataSourceFactory dataSourceFactory;
    private final Environment environment;
    private final Class<?>[] entityClasses;

    protected SessionFactory sessionFactory;

    public SessionFactoryBuilder(DataSourceFactory dataSourceFactory,
                                 Environment environment,
                                 Class<?>[] entityClasses) {

        this.dataSourceFactory = Objects.requireNonNull(dataSourceFactory);
        this.environment = Objects.requireNonNull(environment);
        this.entityClasses = ArrayUtils.addAll(CORE_ENTITY_CLASSES, entityClasses);
    }

    public SessionFactoryBuilder(DataSourceFactory dataSourceFactory, Environment environment) {
        this(dataSourceFactory, environment, new Class<?>[0]);
    }

    @Override
    public SessionFactory get() {
        return build();
    }

    public SessionFactory build() {
        if (sessionFactory == null) {
            log.info("start build");
            ManagedDataSource dataSource = dataSourceFactory.build(environment.metrics(), "hibernate");
            sessionFactory = buildSessionFactory(dataSource, dataSourceFactory.getProperties());
            environment.lifecycle().manage(buildSessionFactoryManager(dataSource, sessionFactory));
        }
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
        for (Class<?> entityClass : entityClasses) {
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

    public final Class<?>[] getEntityClasses() {
        return entityClasses;
    }

}
