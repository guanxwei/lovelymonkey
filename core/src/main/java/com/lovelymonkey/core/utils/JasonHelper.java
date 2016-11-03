package com.lovelymonkey.core.utils;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Utility class to translate object into Jason style string.
 * @author guanxwei
 *
 */
public final class JasonHelper {

    /**
     * Method that is used to transfer an object into Jason style string.
     * @param entity Object that will be transfered.
     * @return The Jason style string.
     * @throws Exception Exception thrown during translation.
     *
     */
    public static String translateObjectToJasonString(final Object entity) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;

        result =  objectMapper.writeValueAsString(entity);

        return result;
    }

    private JasonHelper() { }
}
