package com.lovelymonkey.core.utils;

import java.io.IOException;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author guanxwei
 * Utils class to help translate object into jason style string
 */
public class JasonHelper {
    public static String translateObjectToJasonString(Object entity) throws Exception, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(entity);
    }
}
