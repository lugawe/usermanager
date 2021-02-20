package com.github.lugawe.usermanager.server.model.response;

import com.github.lugawe.usermanager.server.core.auth.AuthRequestFilter;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

public class AuthLogoutResponse implements BaseResponse {

    public AuthLogoutResponse() {
    }

    @Override
    public Response toResponse() {
        NewCookie cookie = AuthRequestFilter.createAccessTokenCookie("");
        return Response.status(200).entity(this).cookie(cookie).build();
    }

}
