package com.github.lugawe.usermanager.db.dao;

import com.github.lugawe.usermanager.db.dao.core.BaseDAO;
import com.github.lugawe.usermanager.model.db.QRole;
import com.github.lugawe.usermanager.model.db.Role;
import com.querydsl.core.types.EntityPath;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class RoleDAO extends BaseDAO<Role> {

    private final QRole role = QRole.role;

    @Inject
    public RoleDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    @Override
    public Optional<Role> tryGet(UUID id) {
        if (id == null) {
            throw new NullPointerException("param id is null");
        }
        Role result = query().where(role.id.eq(id)).fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public final EntityPath<Role> getEntityPath() {
        return role;
    }

}
