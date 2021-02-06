package com.github.lugawe.usermanager.service.auth.jwt;

import com.github.lugawe.usermanager.service.config.JwtConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtHandlerTest {

    private static JwtHandler handler;

    @BeforeAll
    static void init() {
        handler = new JwtHandler(new JwtConfig());
    }

    @Test
    void encode_decode_test() {

        String emptyToken = handler.encode(null);
        assertNotNull(emptyToken);
        assertNotNull(handler.jwt(emptyToken));

    }

}
