package com.lovelymonkey.core.utils;

import org.springframework.util.Assert;

/**
 * Utility class for filters used in the system.
 * @author guanxwei
 *
 */
public final class FilterUtils {

    private FilterUtils() { }

    /**
     * Construct a redirect URL based on the input.
     * @param server The server path that will will be redirected to, must be full path, like "https://####/####".
     * @param key Key used to construct the queryString, used as the key of the queryString.
     * @param value The value corresponding the key in the queryString.
     * @return Full redirectURL path.
     */
    public static String constructRedirectURL(final String server, final String key, final String value) {
        Assert.notNull(server, "Server must be specified");
        StringBuilder sb = new StringBuilder();
        sb.append(server).append(key != null ? "?" + key + "=" + value : "");
        return sb.toString();
    }
}
