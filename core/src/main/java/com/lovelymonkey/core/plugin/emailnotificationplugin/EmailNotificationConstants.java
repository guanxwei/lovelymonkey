package com.lovelymonkey.core.plugin.emailnotificationplugin;

/**
 * EmailNotificationConstants.
 * @author guanxwei
 *
 */
public final class EmailNotificationConstants {

    private EmailNotificationConstants() { }

    /**
     * Status indicates that the email sender queue receive the email successfully.
     */
    public static final String SUCCESS = "APPROVED_AS_REQUESTED";

    /**
     * Status indicates that the email sender queue did not receive the email.
     */
    public static final String FAILED = "PROCESSING_ERROR";

    /**
     * The most times that the requester should retry, once the worker failed to handle the request.
     */
    public static final int RETRY_COUNT = 5;

    /**
     * Default encode type supported by the plug-in.
     */
    public static final String DEFAULT_ENCODE = "UTF-8";

}
