package com.github.lugawe.usermanager.model.db.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BaseEntity implements Persistable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id", updatable = false)
    private UUID id;

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

    @Override
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

}
