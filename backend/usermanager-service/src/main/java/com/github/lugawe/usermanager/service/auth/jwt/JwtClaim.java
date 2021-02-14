package com.github.lugawe.usermanager.service.auth.jwt;

import java.io.Serializable;

public interface JwtClaim extends Serializable {

    String getKey();
    String getValue();

}
