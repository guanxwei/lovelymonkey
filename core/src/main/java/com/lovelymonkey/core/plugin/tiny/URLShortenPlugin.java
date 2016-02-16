package com.lovelymonkey.core.plugin.tiny;

import com.lovelymonkey.core.plugin.Anything;
import com.lovelymonkey.core.plugin.Plugin;

/**
 * Plug-in can be used to shorten the URL for user.
 * @author guanxwei
 *
 */
public class URLShortenPlugin implements Plugin {

    private static final String PLUGIN_NAME = "urlshortenplugin";

    @Override
    public Anything serve(final Anything input) {
        String resourceURL = (String) input.getValue();
        String hashcode = String.valueOf(resourceURL.hashCode());
        String hashURL = "http://" + hashcode;
        Anything output = new Anything();
        output.setValue(hashURL);
        return output;
    }

    @Override
    public String getSymbol() {
        return PLUGIN_NAME;
    }

    @Override
    public boolean isRetriable() {
        return false;
    }

}
