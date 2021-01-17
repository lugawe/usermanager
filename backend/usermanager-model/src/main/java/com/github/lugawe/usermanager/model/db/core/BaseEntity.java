package com.github.lugawe.usermanager.model.db.core;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseEntity implements Comparable<BaseEntity>, Persistable {

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

    public BaseEntity() {
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public int compareTo(BaseEntity entity) {
        return getCreatedAt().compareTo(entity.getCreatedAt());
    }

}
