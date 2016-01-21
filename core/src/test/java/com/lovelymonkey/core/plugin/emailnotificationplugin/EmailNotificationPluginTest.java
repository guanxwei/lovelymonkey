package com.lovelymonkey.core.plugin.emailnotificationplugin;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmailNotificationPluginTest {

    private EmailNotificationPlugin plugin;

    @BeforeMethod
    public void BeforeMethod() {
        this.plugin = new EmailNotificationPlugin();
    }

    @Test
    public void testPluginLoadHappyCase() {
        Assert.assertEquals(plugin.getSymbol(), "emailnotificationplugin");
    }
}
