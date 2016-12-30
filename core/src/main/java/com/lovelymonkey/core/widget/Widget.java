package com.lovelymonkey.core.widget;

import java.util.List;

import com.lovelymonkey.core.widget.data.WidgetInput;

import lombok.Getter;

/**
 * Base class represents web widget in a HTML page. Currently there are two kind of widgets: Menu, Item. The menu level widget
 * will be treated as first level widget and contains some item level widgets. The menu level widgets will be shown in the web
 * pages as menus(like a button in HTML page), customers can click the menus to show the the child widgets in the same menu level
 * widget.
 * @author guanxwei
 *
 */
public abstract class Widget {

    @Getter
    private static final String VERSION = "widget:1.0";

    /**
     * Get widget name.
     * @return widget name.
     */
    public abstract String getName();

    /**
     * Get widget symbol.
     * @return widget symbol.
     */
    public abstract String getSymbol();

    /**
     * Get the widget level of this widget.
     * @return widget level.
     */
    public abstract WidgetLevel getWidgetLevel();

    /**
     * Get the children widgets of this widget.
     * @return children widgets.
     */
    public List<Widget> getChildren() {
        return null;
    }

    /**
     * Get the widget's user authority, default value 1. So that all customers including visitors can visit the widget.
     * @return widget authority
     */
    public int getWidgetAuthority() {
        return 1;
    }

    /**
     * Perform action on the widget.
     */
    public abstract void perform(WidgetInput inut);

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
