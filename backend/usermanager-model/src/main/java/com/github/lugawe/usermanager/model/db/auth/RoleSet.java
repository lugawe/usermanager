package com.github.lugawe.usermanager.model.db.auth;

import com.github.lugawe.usermanager.model.db.core.EntityCore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = RoleSet.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = RoleSet.UC_ROLE_SET_NAME, columnNames = {"name"})
})
public class RoleSet extends EntityCore implements Iterable<Role> {

    public static final String TABLE_NAME = "role_set";
    public static final String UC_ROLE_SET_NAME = "uc_role_set_name";

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @ManyToMany
    @JoinTable(
            name = RoleSet.TABLE_NAME + "_" + Role.TABLE_NAME,
            joinColumns = @JoinColumn(name = RoleSet.TABLE_NAME),
            inverseJoinColumns = @JoinColumn(name = Role.TABLE_NAME)
    )
    private Set<Role> roles;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public RoleSet() {
    }

    @Override
    public Iterator<Role> iterator() {
        if (roles != null) {
            return roles.iterator();
        }
        return Collections.emptyIterator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
