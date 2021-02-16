package com.github.lugawe.usermanager.model.db;

import com.github.lugawe.usermanager.model.db.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User extends BaseEntity implements Principal {

    public enum Type {

        UNDEFINED("undefined"),
        CUSTOM("custom"),
        VISITOR("visitor"),
        USER("user"),
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
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type = Type.UNDEFINED;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password")
    private Password password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_set")
    private RoleSet roleSet;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public User() {
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
