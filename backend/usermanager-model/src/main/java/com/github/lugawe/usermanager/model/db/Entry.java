package com.github.lugawe.usermanager.model.db;

import com.github.lugawe.usermanager.model.db.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = Entry.TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"e_key", User.TABLE_NAME}))
public class Entry extends BaseEntity {

    public static final String TABLE_NAME = "entry";

    @NotNull
    @Column(name = "e_key")
    private String key;

    @NotNull
    @Column(name = "e_value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = User.TABLE_NAME)
    private User user;

    public Entry() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
