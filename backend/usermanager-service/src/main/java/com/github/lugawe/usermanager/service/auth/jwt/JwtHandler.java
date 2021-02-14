package com.github.lugawe.usermanager.service.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.github.lugawe.usermanager.service.config.JwtConfig;
import org.apache.commons.lang3.time.DateUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class JwtHandler {

    private final JwtConfig jwtConfig;
    private final Algorithm algorithm;

    @Inject
    public JwtHandler(JwtConfig jwtConfig) {
        this.jwtConfig = Objects.requireNonNull(jwtConfig);
        this.algorithm = this.jwtConfig.buildAlgorithm();
    }

    public String encode(JwtClaim... claims) {

        Date now = new Date();
        Date expiresAt = DateUtils.addMinutes(now, jwtConfig.getLifetime());

        JWTCreator.Builder builder = JWT.create();
        builder.withIssuedAt(now);
        builder.withExpiresAt(expiresAt);
        builder.withIssuer(jwtConfig.getIssuer());
        builder.withJWTId(UUID.randomUUID().toString());

        for (JwtClaim claim : claims) {
            builder.withClaim(claim.getKey(), claim.getValue());
        }

        return builder.sign(algorithm);
    }

    public DecodedJWT decode(String token) {
        try {
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException("param token is null or empty");
            }
            Verification verification = JWT.require(algorithm);
            verification.withIssuer(jwtConfig.getIssuer());
            return verification.build().verify(token);
        } catch (Exception ex) {
            return null;
        }
    }

    public Optional<String> getClaimValue(String token, String key) {
        try {
            return Optional.of(decode(token).getClaim(key).asString());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public final JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public final Algorithm getAlgorithm() {
        return algorithm;
    }

}
