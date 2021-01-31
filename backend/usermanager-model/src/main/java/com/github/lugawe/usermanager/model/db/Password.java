package com.github.lugawe.usermanager.model.db;

import com.github.lugawe.usermanager.model.db.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "password")
public class Password extends BaseEntity {

    @NotNull
    @Column(name = "hash")
    private String hash;

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

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

}
