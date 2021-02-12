package com.github.lugawe.usermanager.service.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.github.lugawe.usermanager.model.db.User;
import com.github.lugawe.usermanager.service.config.JwtConfig;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

public class JwtHandler {

    private static final Logger log = LoggerFactory.getLogger(JwtHandler.class);

    private static final String USER_CLAIM = "user";

    private final JwtConfig jwtConfig;
    private final Algorithm algorithm;

    @Inject
    public JwtHandler(JwtConfig jwtConfig) {
        this.jwtConfig = Objects.requireNonNull(jwtConfig);
        this.algorithm = this.jwtConfig.buildAlgorithm();
    }

    protected Map<String, Object> userToMap(User user) {
        // TODO
        return new HashMap<>();
    }

    protected User mapToUser(Map<String, Object> map) {
        // TODO
        return new User();
    }

    public String encode(User user, JwtClaim... claims) {

        Date now = new Date();
        Date expiresAt = DateUtils.addMinutes(now, jwtConfig.getLifetime());

        JWTCreator.Builder builder = JWT.create();
        builder.withIssuedAt(now);
        builder.withExpiresAt(expiresAt);
        builder.withIssuer(jwtConfig.getIssuer());
        builder.withJWTId(UUID.randomUUID().toString());

        builder.withClaim(USER_CLAIM, userToMap(user));

        for (JwtClaim claim : claims) {
            builder.withClaim(claim.getKey(), claim.getValue());
        }

        return builder.sign(algorithm);
    }

    public DecodedJWT jwt(String token) {
        try {
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException("param token is null or empty");
            }
            Verification verification = JWT.require(algorithm);
            verification.withIssuer(jwtConfig.getIssuer());
            return verification.build().verify(token);
        } catch (Exception ex) {
            log.warn("failed to decode jwt token", ex);
            return null;
        }
    }

    public Optional<User> decode(String token) {
        DecodedJWT jwt = jwt(token);
        if (jwt != null) {
            Map<String, Object> user = jwt.getClaim(USER_CLAIM).asMap();
            return Optional.of(mapToUser(user));
        }
        return Optional.empty();
    }

    public final JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public final Algorithm getAlgorithm() {
        return algorithm;
    }

}
