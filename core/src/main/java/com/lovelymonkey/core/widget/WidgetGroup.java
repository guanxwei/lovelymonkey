package com.lovelymonkey.core.widget;

import lombok.Getter;

/**
 * WidgetGroup.
 * @author guanxwei
 *
 */
public abstract class WidgetGroup {

    @Getter
    private static final String VERSION = "widgetgroup:1.0";

    /**
     * Get widget group name.
     * @return group name.
     */
    protected abstract String getName();

}
