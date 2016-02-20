package com.lovelymonkey.core.plugin.emailnotificationplugin;

import com.lovelymonkey.core.plugin.PluginException;

/**
 * EmailNotificationPluin specific exception type exposed for client, to notify client that there is something wrong happened
 * client should take some action when this kind exception happens.
 * @author guanxwei
 *
 */
public class EmailSendExeception extends PluginException {

    private static final long serialVersionUID = -4106471709326821053L;

    /**
     * Construct an <code>EmailSendExeception</code> without detail message.
     */
    public EmailSendExeception() {
        super();
    }

    /**
     * Construct an <code>EmailSendExeception</code> with detail message.
     * @param message The detail message.
     */
    public EmailSendExeception(final String message) {
        super(message);
    }

    /**
     * Construct an <code>EmailSendExeception</code> with source throwable.
     * @param t The root cause.
     */
    public EmailSendExeception(final Throwable t) {
        super(t);
    }
}
