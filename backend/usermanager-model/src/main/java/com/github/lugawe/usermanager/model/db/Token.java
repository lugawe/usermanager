package com.github.lugawe.usermanager.model.db;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "token")
public class Token extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "token_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "expires_at")
    private DateTime expiresAt;

    @NotNull
    @Column(name = "last_access")
    private DateTime lastAccess;

    public Token() {
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(DateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public DateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(DateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

}
