package com.lovelymonkey.core.widget;

import lombok.Getter;

/**
 * Base class represents web widget in a html page.
 * @author guanxwei
 *
 */
public abstract class Widget {

    @Getter
    private static final String VERSION = "widget:1.0";

    protected abstract String getGroupName();

    protected abstract String getName();

    protected abstract String getSymbol();
}
