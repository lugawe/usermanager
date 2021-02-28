package com.github.lugawe.usermanager.model.db.auth;

import com.github.lugawe.usermanager.model.db.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = Password.TABLE_NAME)
public class Password extends BaseEntity {

    public static final String TABLE_NAME = "password";

    @NotNull
    @Column(name = "hash")
    private String hash;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    public Password() {
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

}
