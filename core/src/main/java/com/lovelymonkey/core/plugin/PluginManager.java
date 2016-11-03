package com.lovelymonkey.core.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.lovelymonkey.core.service.PluginSystemSpecificService;

import lombok.extern.slf4j.Slf4j;

/**
 * Plug-in Manager who owns all the plug-ins in the system, customer who wants to call a plug-in to meet his demand should,
 * cooperate with Plug-in Manager, Plug-in Manager will delegate his request to the specific plug-in, hence all the customer
 * code will keep the same and wont need to care about the potential change in plug-in.
 * @author wgx
 *
 */
@Slf4j
public class PluginManager {

    @Autowired
    private PluginSystemSpecificService pluginSystemSpecificService;

    private final Map<String, Plugin> plugins = new HashMap<String, Plugin>();

    /**
     * Return the available plug-ins managed by this manager.
     * @return The plug-ins managed by this manager.
     */
    public List<Plugin> getAvailablePlugins() {
        return Lists.newArrayList(plugins.values());
    }

    /**
     * Return the available plug-in by plug-in name.
     * @param pluginName The name of a plug-in which is managed by this manager.
     * @return Plug-in whose name is plug-inName.
     */
    public Plugin getPluginByName(final String pluginName) {
        return plugins.get(pluginName);
    }

    /**
     * Register a plug-in into the plug-in register.
     * @param target Plug-in that need to be registered.
     */
    public void registerPlugin(final Plugin target) {
        boolean pluginExists = plugins.containsKey(target.getSymbol());
        if (pluginExists) {
            log.info("Trying to register existed plug-in, will overwrite the plug-in");
        }
        plugins.put(target.getSymbol(), target);
    }

    /**
     * Check if the plug-in has been registered.
     * @param plugin Plug-in need to be checked.
     * @return Existed status.
     */
    public boolean isRegistered(final Plugin plugin) {
        return plugins.containsKey(plugin.getSymbol());
    }

    /**
     * Fetch the plug-in system specific service.
     * @return Plug-in system specific service.
     */
    public PluginSystemSpecificService fetchStorage() {
        return pluginSystemSpecificService;
    }
}
