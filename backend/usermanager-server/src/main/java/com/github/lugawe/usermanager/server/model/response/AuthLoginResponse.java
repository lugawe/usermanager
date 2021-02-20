package com.github.lugawe.usermanager.server.model.response;

import com.github.lugawe.usermanager.server.core.auth.AuthRequestFilter;

import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

public class AuthLoginResponse implements BaseResponse {

    private boolean success;
    private String refreshToken;
    private String accessToken;

    public AuthLoginResponse() {
    }

    public AuthLoginResponse(boolean success, String refreshToken, String accessToken) {
        this.success = success;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    @Override
    public Response toResponse() {
        NewCookie cookie = AuthRequestFilter.createAccessTokenCookie(accessToken);
        return Response.status(success ? 200 : 401).entity(this).cookie(cookie).build();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
