package com.github.lugawe.usermanager.model.db;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "token")
public class Token extends BaseEntity {

    public enum Type {

        CUSTOM("custom"),
        ID("id"),
        RANDOM("random"),
        REFRESH("refresh"),
        ACCESS("access");

        private final String type;

        private Type(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "token_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "value")
    private String value;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type = Type.CUSTOM;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type == null) {
            throw new NullPointerException();
        }
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
