package com.lovelymonkey.core.widget;

import lombok.Getter;

public abstract class WidgetGroup {

    @Getter
    private static final String VERSION = "widgetgroup:1.0";

    protected abstract String getName();

}
