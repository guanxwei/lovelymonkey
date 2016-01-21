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

    /**
     * Get widget group name.
     * @return group name
     */
    protected abstract String getGroupName();

    /**
     * Get widget name.
     * @return widget name.
     */
    protected abstract String getName();

    /**
     * Get widget symbol.
     * @return widget symbol.
     */
    protected abstract String getSymbol();
}
