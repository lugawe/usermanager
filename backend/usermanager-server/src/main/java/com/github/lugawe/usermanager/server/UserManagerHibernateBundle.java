package com.github.lugawe.usermanager.server;

import com.github.lugawe.usermanager.model.db.*;
import com.github.lugawe.usermanager.model.db.base.BaseEntity;
import com.github.lugawe.usermanager.model.db.base.Persistable;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class UserManagerHibernateBundle extends HibernateBundle<UserManagerConfiguration> {

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

    public UserManagerHibernateBundle() {
        super(Persistable.class, ENTITY_CLASSES);
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(UserManagerConfiguration configuration) {
        return configuration.getDatabase();
    }

}
