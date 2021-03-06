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
     * @param widget widget.
     */
    public void register(@NonNull final Widget widget) {
        Widget exists = widgets.get(widget.getName());
        if (exists != null) {
            log.warn("Trying to register exists widget [{}]", exists.getSymbol());
        }
        widgets.put(widget.getName(), widget);
    }

    /**
     * Get a widget from register by name.
     * @param name name.
     * @return widget name.
     */
    public Widget getWidget(@NonNull final String name) {
        return widgets.get(name);
    }

    /**
     * Return all the widgets registered in the register.
     * @return widget list.
     */
    public List<Widget> getAll() {
        return (List<Widget>) widgets.values();
    }
}
