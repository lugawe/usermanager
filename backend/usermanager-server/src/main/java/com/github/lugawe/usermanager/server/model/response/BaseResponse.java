package com.github.lugawe.usermanager.server.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public interface BaseResponse extends Serializable {

    @JsonIgnore
    Response toResponse();

}
