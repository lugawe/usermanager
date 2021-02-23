package com.github.lugawe.usermanager.server.health;

import com.codahale.metrics.health.HealthCheck;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Objects;

public class DatabaseHealthCheck extends HealthCheck {

    private final SessionFactory sessionFactory;

    @Inject
    public DatabaseHealthCheck(SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

    public final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
