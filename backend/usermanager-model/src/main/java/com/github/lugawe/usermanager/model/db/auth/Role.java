package com.github.lugawe.usermanager.model.db.auth;

import com.github.lugawe.usermanager.model.db.core.EntityCore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Role.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = Role.UC_ROLE_NAME, columnNames = {"name"})
})
public class Role extends EntityCore {

    public static final String TABLE_NAME = "role";
    public static final String UC_ROLE_NAME = "uc_role_name";

    @NotNull
    @Column(name = "name")
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
