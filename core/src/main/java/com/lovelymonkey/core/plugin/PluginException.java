package com.lovelymonkey.core.plugin;

/**
 * Base exception type of the plug-in system.
 * @author guanxwei
 *
 */
public class PluginException extends Exception {

    private static final long serialVersionUID = 8051916937254312376L;

    /**
     * Construct an <code>PluginException</code> without detail message.
     */
    public PluginException() {
        super();
    }

    /**
     * Construct an <code>PluginException</code> with detail message.
     * @param message The detail message.
     */
    public PluginException(final String message) {
        super(message);
    }

    /**
     * Construct an <code>PluginException</code> with source throwable.
     * @param t The root cause.
     */
    public PluginException(final Throwable t) {
        super(t);
    }
}
