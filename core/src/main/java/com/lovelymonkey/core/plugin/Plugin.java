package com.lovelymonkey.core.plugin;

/**
 * A plugin is an extra service provider for project. This class is the base of the plugin system,
 * every user specific plugin should implements this class, and realize their function by override the function serve();
 * @author wgx
 * @param <T>
 *
 */
public interface Plugin {
    
    /**
     * The base function that should be override by its sub class
     */
    public Anything serve(Anything input);
}
