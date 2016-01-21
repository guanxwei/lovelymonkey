package com.lovelymonkey.core.plugin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PluginManagerTest {

    private PluginManager manager;
    private TestPlugin plugin;

    @BeforeMethod
    public void BeforeMethod() {
        this.manager = new PluginManager();
        this.plugin = new TestPlugin();
    }

    /**
     * Test plug-in can be registered.
     */
    @Test
    public void testPluginRegisterHappyCase() {

        manager.registerPlugin(plugin);

        Assert.assertEquals(manager.getAvailablePlugins().size(), 1);
        Assert.assertEquals(manager.getPluginByName("test"), plugin);
        Assert.assertEquals(manager.isRegistered(plugin), true);
    }

    /**
     * Test plug-in will be over written.
     */
    @Test
    public void testPluginOnlyExistOne() {
        manager.registerPlugin(plugin);
        manager.registerPlugin(new TestPlugin());

        Assert.assertEquals(manager.getAvailablePlugins().size(), 1);
        Assert.assertNotEquals(manager.getPluginByName("test"), plugin);
    }

}
