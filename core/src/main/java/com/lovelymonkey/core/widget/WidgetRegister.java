package com.lovelymonkey.core.widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * Widget register.
 * @author guanxwei
 *
 */
@Slf4j
public class WidgetRegister {

    private final Map<String, Widget> widgets = new HashMap<String, Widget>();

    /**
     * Register a widget by its name. Every existed widget will be overwritten.
     * @param widget
     */
    public void register(@NonNull final Widget widget) {
        Widget exists = widgets.get(widget.getSymbol());
        if (exists != null) {
            log.warn("Trying to register exists widget [{}]", exists.getSymbol());
        }
        widgets.put(widget.getSymbol(), widget);
    }

    /**
     * Get a widget from register by name.
     * @param name
     * @return
     */
    public Widget getWidget(@NonNull final String name) {
        return widgets.get(name);
    }

    /**
     * Return all the widgets registerd in the register.
     * @return
     */
    public List<Widget> getAll() {
        return (List<Widget>) widgets.values();
    }
}
