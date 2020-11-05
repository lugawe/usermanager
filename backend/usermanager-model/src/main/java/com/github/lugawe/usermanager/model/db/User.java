package com.github.lugawe.usermanager.model.db;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "user_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password", referencedColumnName = "password_id")
    private Password password;

    public User() {
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

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

}
