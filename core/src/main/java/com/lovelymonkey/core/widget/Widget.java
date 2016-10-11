package com.lovelymonkey.core.widget;

import lombok.Getter;

/**
 * Base class represents web widget in a html page. Currently there are two kind of widgets: Menu, Item. The menu level widget
 * will be treated as first level widget and contains some item level widgets. The menu level widgets will be shown in the web
 * pages as menus(like a button in html page), customers can click the menus to show the the child widgets in the same menu level
 * widget.
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
    protected abstract WidgetGroup getGroup();

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

    /**
     * Get the widget level of this widget.
     * @return widget level.
     */
    protected abstract WidgetLevel getWidgetLevel();

    /**
     * Widget level.
     * @author guanxwei
     *
     */
    public enum WidgetLevel {

        // CHECKSTYLE:OFF
        /**
         * Menu level.
         */
        MENU(1),

        /**
         * Item level.
         */
        ITEM(2);

        private int level;

        private WidgetLevel(final int level) {
            this.level = level;
        }

        // CHECKSTYLE:ON
        public int getLevel() {
            return this.level;
        }
    }

}
