package com.ebi.interview.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** The type App utils. */
public class AppUtils {

    /**
     * Stringify string.
     *
     * @param <T> the type parameter
     * @param object the object
     * @return the string
     */
    public static <T> String stringify(T object) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String objectAsString = null;
        try {
            objectAsString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectAsString;
    }

    /**
     * Objectify t.
     *
     * @param <T> the type parameter
     * @param objectAsString the object as string
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T objectify(final String objectAsString, Class<T> clazz) {
        final ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = objectMapper.readValue(objectAsString, clazz);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return object;
    }
}
