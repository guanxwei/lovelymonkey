package com.lovelymonkey.core.activity.widget;

/**
 * Widget Activities used util.
 * @author guanxwei
 *
 */
public final class WidgetUtil {

    private WidgetUtil() { }

    /**
     * Reference to a widget. Each timer customer wants to use a widget, the work-flow engine will assign the target widget
     * this reference. Since each work flow instance is initiated in single thread, so these widgets will not run into conflict
     * as they have the same reference.
     */
    public static final String WIDGET_RESOURCE_REFERENCE = "widget::resource::reference";
}
