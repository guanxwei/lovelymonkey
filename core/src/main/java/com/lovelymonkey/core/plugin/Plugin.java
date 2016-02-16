package com.lovelymonkey.core.plugin;

/**
 * A plug-in is an extra service provider for project. This class is the base of the plug-in system,
 * every user specific plug-in should implements this class, and realize their function by override the function serve();
 * @author wgx
 *
 */
public interface Plugin {

    /**
     * The base function that should be override by its sub class.
     * @param input Input object contains the information that need to be handled by the plug-in.
     * @return The response output by plug-in.
     */
    Anything serve(final Anything input);

    /**
     * Return the symbol name of the plug-in.
     * @return The symbol name.
     */
    String getSymbol();

    /**
     * Tag to indicate that if the plug-in supports retry action.
     * @return The tag to indicate if the plug-in support retry action once failed.
     */
    boolean isRetriable();
}
