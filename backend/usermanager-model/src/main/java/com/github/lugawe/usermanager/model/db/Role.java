package com.github.lugawe.usermanager.model.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "role_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    public Role() {
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

}
