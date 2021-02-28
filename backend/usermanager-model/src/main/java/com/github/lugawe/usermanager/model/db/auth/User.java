package com.github.lugawe.usermanager.model.db.auth;

import com.github.lugawe.usermanager.model.db.core.EntityCore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDateTime;

@Entity
@Table(name = User.TABLE_NAME)
public class User extends EntityCore implements Principal {

    public static final String TABLE_NAME = "um_user";

    public enum Type {

        UNDEFINED("undefined"),
        CUSTOM("custom"),
        VISITOR("visitor"),
        BASIC_USER("basic_user"),
        VERIFIED_USER("verified_user"),
        MANAGER("manager"),
        ADMIN("admin");

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
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type = Type.UNDEFINED;

    @NotNull
    @OneToOne
    @JoinColumn(name = Password.TABLE_NAME)
    private Password password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = RoleSet.TABLE_NAME)
    private RoleSet roleSet;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public User() {
    }

    @Override
    public String getName() {
        return getId().toString();
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

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public RoleSet getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(RoleSet roleSet) {
        this.roleSet = roleSet;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
