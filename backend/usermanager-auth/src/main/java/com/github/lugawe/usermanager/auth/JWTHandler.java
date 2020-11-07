package com.github.lugawe.usermanager.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.github.lugawe.usermanager.util.Mapper;
import org.apache.commons.lang3.ClassUtils;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;

public class JWTHandler {

    private final Algorithm algorithm;
    private final JWTConfig config;

    public JWTHandler(Algorithm algorithm, JWTConfig config) {
        this.algorithm = Objects.requireNonNull(algorithm);
        this.config = Objects.requireNonNull(config);
    }

    public JWTHandler(Algorithm algorithm) {
        this(algorithm, new JWTConfig());
    }

    private void applyConfig(JWTCreator.Builder builder, DateTime now) {
        builder.withIssuer(config.getIssuer());
        builder.withExpiresAt(now.plus(config.getLifetime()).toDate());
    }

    private void addClaim(JWTCreator.Builder builder, JWTClaim<?> claim) {
        Object value = claim.getValue();
        if (ClassUtils.isPrimitiveOrWrapper(value.getClass()) || value instanceof String) {
            builder.withClaim(claim.getName(), value.toString());
        } else if (value instanceof Date) {
            builder.withClaim(claim.getName(), (Date) value);
        } else if (value instanceof DateTime) {
            builder.withClaim(claim.getName(), ((DateTime) value).toDate());
        } else {
            builder.withClaim(claim.getName(), Mapper.toMap(value));
        }
    }

    public String encode(JWTClaim<?> ...claims) {

        DateTime now = DateTime.now();

        JWTCreator.Builder builder = JWT.create();
        builder.withIssuedAt(now.toDate());

        applyConfig(builder, now);

        for (JWTClaim<?> claim : claims) {
            addClaim(builder, claim);
        }

        return builder.sign(getAlgorithm());
    }

    public DecodedJWT decode(String token) {
        try {
            Verification verification = JWT.require(algorithm);
            verification.withIssuer(config.getIssuer());
            return verification.build().verify(token);
        } catch (Exception ex) {
            return null;
        }
    }

    public <T> T decode(String token, String claim, Class<T> tClass) {
        try {
            return Objects.requireNonNull(decode(token)).getClaim(claim).as(tClass);
        } catch (Exception ex) {
            return null;
        }
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public JWTConfig getConfig() {
        return config;
    }

}
