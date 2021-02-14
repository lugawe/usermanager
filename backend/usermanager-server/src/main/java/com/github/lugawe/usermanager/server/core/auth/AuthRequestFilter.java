package com.github.lugawe.usermanager.server.core.auth;

import com.github.lugawe.usermanager.model.db.User;
import io.dropwizard.auth.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.Objects;

public class AuthRequestFilter extends AuthFilter<String, User> {

    private static final Logger log = LoggerFactory.getLogger(AuthRequestFilter.class);

    public static final String COOKIE_ACCESS_TOKEN = "access_token";

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        Cookie cookie = context.getCookies().get(COOKIE_ACCESS_TOKEN);
        if (cookie != null) {
            String accessToken = cookie.getValue();
            if (accessToken != null && !accessToken.trim().isEmpty()) {
                if (authenticate(context, accessToken, SecurityContext.BASIC_AUTH)) {
                    log.info("authenticate ok - {}", info(context));
                }
            }
        }
    }

    protected String info(ContainerRequestContext context) {
        try {
            return context.getUriInfo().toString();
        } catch (Exception ex) {
            return "unknown";
        }
    }

    public static class Builder extends AuthFilterBuilder<String, User, AuthRequestFilter> {

        public Builder() {
        }

        @Inject
        public Builder(UserCoreAuthenticator authenticator, UserCoreAuthorizer authorizer) {
            this();
            setAuthenticator(Objects.requireNonNull(authenticator));
            setAuthorizer(Objects.requireNonNull(authorizer));
        }

        @Override
        protected AuthRequestFilter newInstance() {
            return new AuthRequestFilter();
        }

    }

}
