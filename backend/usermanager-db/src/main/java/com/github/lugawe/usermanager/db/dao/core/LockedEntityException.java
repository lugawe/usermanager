package com.github.lugawe.usermanager.db.dao.core;

import com.github.lugawe.usermanager.model.db.core.Persistable;

public class LockedEntityException extends RuntimeException {

    private Persistable entity;

    public LockedEntityException() {
        super();
    }

    public LockedEntityException(String message) {
        super(message);
    }

    public LockedEntityException(String message, Persistable entity) {
        this(message);
        this.entity = entity;
    }

    public LockedEntityException(Persistable entity) {
        this("Entity is locked", entity);
    }

    public Persistable getEntity() {
        return entity;
    }

    public void setEntity(Persistable entity) {
        this.entity = entity;
    }

}
