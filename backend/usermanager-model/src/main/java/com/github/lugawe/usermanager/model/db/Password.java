package com.github.lugawe.usermanager.model.db;

import com.github.lugawe.usermanager.model.db.core.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "password")
public class Password extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "password_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "hash")
    private String hash;

    @NotNull
    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    public Password() {
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

}
