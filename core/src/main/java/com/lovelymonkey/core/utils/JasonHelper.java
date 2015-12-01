package com.lovelymonkey.core.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Utils class to help translate object into jason style string.
 * @author guanxwei
 *
 */
public final class JasonHelper {

    /**
     * Method that is used to transfer an object into jason style string.
     * @param entity Object that will be transferd.
     * @return The jason style string.
     *
     */
    public static String translateObjectToJasonString(final Object entity) {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = null;

        try {
            result =  objectMapper.writeValueAsString(entity);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    private JasonHelper() { }
}
