package com.lovelymonkey.core.plugin;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Plugin Manager who owns all the plugins in the system, customer who wants to call a plugin to meet his demand should,
 * cooperate with Plugin Manager, Plugin Manager will delegate his request to the specific plugin, hence all the customer
 * code will keep the same and wont need to care about the potential change in plugin.
 * @author wgx
 *
 */
public class PluginManager {
    @Getter @Setter
    private Map<String, Plugin> plugins;

    /**
     * Return the available plugins managed by this manager.
     * @return The plugins managed by this manager.
     */
    public List<Plugin> getAvailablePlugins() {
        return (List<Plugin>) plugins.values();
    }

    /**
     * Return the available plugin by plugin name.
     * @param pluginName The name of a plugin which is managed by this manager.
     * @return Plugin whose name is pluginName.
     */
    public Plugin getPluginByName(final String pluginName) {
        return plugins.get(pluginName);
    }

}
