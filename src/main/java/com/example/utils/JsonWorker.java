package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonWorker {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> String toJSON(T object) throws IOException {
        return mapper.writeValueAsString(object);
    }
    public static <T> T toObject(Class<T> tClass, String json) throws IOException {
        return mapper.readValue(json, tClass);
    }
}
