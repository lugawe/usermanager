package com.github.lugawe.usermanager.model.db;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public abstract class BaseEntity implements Persistable {

    @NotNull
    @Column(name = "created_at", updatable = false)
    private DateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

    public BaseEntity() {
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

}
