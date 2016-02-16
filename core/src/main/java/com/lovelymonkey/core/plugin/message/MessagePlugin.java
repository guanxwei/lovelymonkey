package com.lovelymonkey.core.plugin.message;

import com.lovelymonkey.core.plugin.Anything;
import com.lovelymonkey.core.plugin.Plugin;

/**
 * Plug-in can be used to send message to other users.
 * @author guanxwei
 *
 */
public class MessagePlugin implements Plugin {

    private static final String PLUGIN_NAME = "messageplugin";

    /**
     * {@inheritDoc}
     */
    @Override
    public Anything serve(final Anything input) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSymbol() {
        return PLUGIN_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRetriable() {
        return true;
    }

}
