package com.lovelymonkey.core.widget;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class represents web widget in a html page.
 * @author guanxwei
 *
 */
public class Widget {

    @Getter
    private static final String WIDGET_VERSION = "widget:1.0";

    @Getter
    private static final String WIDGET_NAME = "BASE_WIDGET";

    @Getter @Setter
    private String widgetContent;

}
