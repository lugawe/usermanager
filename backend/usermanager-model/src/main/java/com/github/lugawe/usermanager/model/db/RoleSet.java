package com.github.lugawe.usermanager.model.db;

import com.github.lugawe.usermanager.model.db.core.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role_set")
public class RoleSet extends BaseEntity implements Iterable<Role> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "role_set_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_set_role", joinColumns = @JoinColumn(name = "role_set"), inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    public RoleSet() {
    }

    @Override
    public Iterator<Role> iterator() {
        if (roles != null) {
            return roles.iterator();
        }
        return Collections.emptyIterator();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
