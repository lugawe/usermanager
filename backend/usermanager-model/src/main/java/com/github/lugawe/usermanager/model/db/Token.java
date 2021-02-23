package com.github.lugawe.usermanager.model.db;

import com.github.lugawe.usermanager.model.db.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = Token.TABLE_NAME)
public class Token extends BaseEntity {

    public static final String TABLE_NAME = "token";

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

        @Override
        public String toString() {
            return type;
        }

    }

    @NotNull
    @Column(name = "value")
    private String value;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type = Type.CUSTOM;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @NotNull
    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    public Token() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

}
