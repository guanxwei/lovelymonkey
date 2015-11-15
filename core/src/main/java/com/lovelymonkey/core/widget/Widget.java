package com.lovelymonkey.core.widget;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author guanxwei
 * Widget is the base class represents module in a web page, 
 */
public class Widget {

    private static final String WIDGET_VERSION = "widget:1.0";

    private static final String WIDGET_NAME = "BASE_WIDGET";

    @Getter @Setter
    private String WIDGET_CONTENT_STRING;

}
