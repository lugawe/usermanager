package com.github.lugawe.usermanager.model.db.auth;

import com.github.lugawe.usermanager.model.db.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Role.TABLE_NAME)
public class Role extends BaseEntity {

    public static final String TABLE_NAME = "role";

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
