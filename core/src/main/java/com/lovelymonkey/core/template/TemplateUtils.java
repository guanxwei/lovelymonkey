package com.lovelymonkey.core.template;

/**
 * Utility class for templates. Currently, we are using jsp to render pages.
 * @author guanxwei
 *
 */
public final class TemplateUtils {

    private TemplateUtils() { }

    private static final String TEMPLATE_FOLDER = "template/";

    /**
     * Default error handler page, customers will be directed to this page when errors occur.
     */
    public static final String DEFAULT_ERROR_PAGE = TEMPLATE_FOLDER + "error";

    /**
     * Default alarm handler page, customers will be directed to this page when they are not granted to.
     */
    public static final String DEFAULT_ALARM_PAGE = TEMPLATE_FOLDER + "alarm";

    /**
     * Default widget not found handler page, customers will be directed to this page when they
     * are visiting a not existing widget.
     */
    public static final String DEFAULT_WIDGET_NOT_FOUND_PAGE = TEMPLATE_FOLDER + "widgetnotfound";

    /**
     * Default widget not granted handler page, customers will be directed to this page when they
     * are visiting a not granted widget.
     */
    public static final String DEFAULT_WIDGET_NOT_GRANTED_PAGE = TEMPLATE_FOLDER + "widgetnotgranted";

    /**
     * Return page reference.
     */
    public static final String RETURN_PAGE = TEMPLATE_FOLDER + "returnPage";

    /**
     * Logig page.
     */
    public static final String LOGIN_PAGE = "login";
}
