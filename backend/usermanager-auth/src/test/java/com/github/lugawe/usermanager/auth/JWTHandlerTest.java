package com.github.lugawe.usermanager.auth;

import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JWTHandlerTest {

    private static class Person {
        public String name;
        public int age;
    }

    private static Person person1;
    private static Person person2;

    private static JWTHandler handler1;
    private static JWTHandler handler2;

    @BeforeAll
    public static void init() {

        person1 = new Person();
        person1.name = "p1";
        person1.age = 21;

        person2 = new Person();
        person2.name = "p2";
        person2.age = 42;

        handler1 = new JWTHandler(Algorithm.HMAC256("test1"));
        handler2 = new JWTHandler(Algorithm.HMAC256("test2"));
    }

    @Test
    public void encode_decode() {

        String t1 = handler1.encode(
                new JWTClaim<>("hello", "world"),
                new JWTClaim<>("p1", person1),
                new JWTClaim<>("p2", person2));

        assertNotNull(t1);

        Person p1 = handler1.decode(t1, "p1", Person.class);
        assertEquals(p1.name, person1.name);
        assertEquals(p1.age, person1.age);

        Person p2 = handler1.decode(t1, "p2", Person.class);
        assertEquals(p2.name, person2.name);
        assertEquals(p2.age, person2.age);

        assertNull(handler1.decode(t1, "p3", Person.class));

        assertNull(handler2.decode(t1));
        assertNull(handler2.decode(t1, "p1", Person.class));

    }

}
