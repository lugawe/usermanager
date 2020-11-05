package com.github.lugawe.usermanager.model.db;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private DateTime lastAccess;

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

    public DateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(DateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

}
