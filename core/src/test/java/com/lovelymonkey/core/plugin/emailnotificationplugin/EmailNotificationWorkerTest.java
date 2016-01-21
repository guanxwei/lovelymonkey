package com.lovelymonkey.core.plugin.emailnotificationplugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lovelymonkey.core.functional.TestBase;

public class EmailNotificationWorkerTest extends TestBase{

    @Autowired
    private EmailNotificationWorker worker;

    @Autowired
    private JavaMailSender sender;

    @BeforeClass
    public void BeforeClass() {
        //this.worker = new EmailNotificationWorker();
    }

    @Test
    public void testWorkerInitiateHappyCase() {
        Assert.assertNotNull(worker);
        Assert.assertNotNull(sender);
        Assert.assertTrue(sender instanceof JavaMailSenderImpl);
        JavaMailSenderImpl realSender = (JavaMailSenderImpl) sender;
        Assert.assertEquals(realSender.getHost(), "smtp.exmail.qq.com");
        Assert.assertEquals(realSender.getPort(), 465);
        Assert.assertEquals(realSender.getDefaultEncoding(), "UTF-8");
        Assert.assertEquals(realSender.getProtocol(), "smtp");
        Assert.assertEquals(realSender.getUsername(), "1026189878@qq.com");
        Assert.assertEquals(realSender.getPassword(), "qweQWE123");
    }

    @Test
    public void testWorkerThread() {
       
    }
}
