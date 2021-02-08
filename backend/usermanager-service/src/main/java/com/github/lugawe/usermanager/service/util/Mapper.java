package com.github.lugawe.usermanager.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class Mapper {

    private Mapper() {
    }

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static <T> Map<String, Object> toMap(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return getObjectMapper().convertValue(value, new TypeReference<Map<String, Object>>() {});
    }

    public static <T> T fromMap(Map<String, Object> value, Class<T> tClass) {
        if (value == null || tClass == null) {
            throw new NullPointerException();
        }
        return getObjectMapper().convertValue(value, tClass);
    }

}
