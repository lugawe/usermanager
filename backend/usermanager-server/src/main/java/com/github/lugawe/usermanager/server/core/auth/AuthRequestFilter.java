package com.github.lugawe.usermanager.server.core.auth;

import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.server.util.CookieBuilder;
import io.dropwizard.auth.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;

public class AuthRequestFilter extends AuthFilter<String, User> {

    private static final Logger log = LoggerFactory.getLogger(AuthRequestFilter.class);

    public static final String COOKIE_ACCESS_TOKEN = "access_token";
    public static final String COOKIE_ACCESS_TOKEN_PATH = "/api/";
    public static final int COOKIE_ACCESS_TOKEN_EXPIRY = 60 * 60 * 24 * 365;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        Cookie cookie = context.getCookies().get(COOKIE_ACCESS_TOKEN);
        if (cookie != null) {
            String accessToken = cookie.getValue();
            if (accessToken != null && !accessToken.trim().isEmpty()) {
                if (authenticate(context, accessToken, SecurityContext.BASIC_AUTH)) {
                    log.info("authenticate ok - {}", info(context));
                    return;
                }
            }
        }
        throw new WebApplicationException(unauthorizedHandler.buildResponse(prefix, realm));
    }

    protected String info(ContainerRequestContext context) {
        try {
            return context.getUriInfo().toString();
        } catch (Exception ex) {
            return "unknown";
        }
    }

    public static NewCookie createAccessTokenCookie(String token) {
        return new CookieBuilder()
                .name(COOKIE_ACCESS_TOKEN)
                .value(token)
                .httpOnly(true)
                .path(COOKIE_ACCESS_TOKEN_PATH)
                .lifetime(COOKIE_ACCESS_TOKEN_EXPIRY)
                .build();
    }

    public static class Builder extends AuthFilterBuilder<String, User, AuthRequestFilter> {

        public Builder() {
            setRealm("");
            setPrefix("");
        }

        @Inject
        public Builder(UserCoreAuthenticator authenticator, UserCoreAuthorizer authorizer) {
            this();
            setAuthenticator(authenticator);
            setAuthorizer(authorizer);
        }

        @Override
        protected AuthRequestFilter newInstance() {
            return new AuthRequestFilter();
        }

    }

}
