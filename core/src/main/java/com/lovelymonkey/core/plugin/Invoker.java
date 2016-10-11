package com.lovelymonkey.core.plugin;

/**
 * Encapsulation of single worker for each {@link Plugin} instance. {@link Plugin} instances can invoke a internal invoker
 * to handle the logic they want, customer can choose to use a invoker or not to fulfill their requirement. Invoker provides
 * a unified strategy to tackle different things, customers can easily upgrade their plug-in by changing the invoker their used
 * to invoke. So we strongly recommend to use invoker to realize plug-in specific logic.
 */
public interface Invoker {

    /**
     * Entry to invokers specific logic, host usually a {@link Plugin} instance will invoke this method.
     * @param input The input to the invoker.
     * @return Result returned by the invoker.
     */
    Anything invoke(Anything input);
}
