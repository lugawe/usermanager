package com.github.lugawe.usermanager.model.db;

import java.io.Serializable;
import java.util.UUID;

public interface Persistable extends Serializable {

    UUID getId();

}
