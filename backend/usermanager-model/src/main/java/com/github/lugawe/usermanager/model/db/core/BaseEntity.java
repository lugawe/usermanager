package com.github.lugawe.usermanager.model.db.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseEntity implements Comparable<BaseEntity>, Persistable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

    public BaseEntity() {
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
