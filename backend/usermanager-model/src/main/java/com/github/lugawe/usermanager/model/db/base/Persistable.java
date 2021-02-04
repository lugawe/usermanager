package com.github.lugawe.usermanager.model.db.base;

import java.io.Serializable;
import java.util.UUID;

public interface Persistable extends Serializable {

    UUID getId();

    boolean isLocked();

}
