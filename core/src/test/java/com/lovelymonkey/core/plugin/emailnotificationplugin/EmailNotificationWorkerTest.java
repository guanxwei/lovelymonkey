package com.lovelymonkey.core.plugin.emailnotificationplugin;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.lovelymonkey.core.functional.TestBase;
import com.lovelymonkey.core.plugin.emailnotificationplugin.builder.EmailBuilder;
import com.lovelymonkey.core.plugin.emailnotificationplugin.builder.ReceiverBuilder;

public class EmailNotificationWorkerTest extends TestBase{

    @Autowired
    private EmailNotificationWorker worker;

    @Autowired
    private JavaMailSenderImpl realSender;

    private GreenMail greenMail;

    //@BeforeMethod
    public void BeforeMethod() {
        this.greenMail = new GreenMail(ServerSetup.SMTPS);
        greenMail.setUser("1026189878@qq.com", "qweQWE123");
        greenMail.start();
    }

    //@AfterMethod
    public void AfterMethod() {
        greenMail.stop();
    }

    @Test
    public void testWorkerInitiateHappyCase() {
        Assert.assertNotNull(worker);
        Assert.assertNotNull(realSender);
        Assert.assertEquals(realSender.getHost(), "smtp.qq.com");
        Assert.assertEquals(realSender.getPort(), 465);
        Assert.assertEquals(realSender.getDefaultEncoding(), "UTF-8");
        Assert.assertEquals(realSender.getProtocol(), "smtp");
        Assert.assertEquals(realSender.getUsername(), "1026189878@qq.com");
        Assert.assertEquals(realSender.getPassword(), "qweQWE123");
    }

    @Test
    public void testWorkerThreadsInitiateHappyCase() throws Exception {
       Field threads = worker.getClass().getDeclaredField("THREADS");
       threads.setAccessible(true);
       Thread[] value_of_threads = (Thread[]) threads.get(worker);

       Assert.assertEquals(value_of_threads.length, 5);
       int count = 0;
       for (Thread t : value_of_threads) {
           Assert.assertEquals(t.getName(), "EmailSender" + count);
           count ++;
       }
       threads.setAccessible(false);
    }

    @Test
    public void testConnection() throws Exception {
       realSender.testConnection();
    }

    @Test
    public void testSendEmail() throws Exception {
        Email email = EmailBuilder.builder()
                .content("Test email content")
                .subject("Test email")
                .receiver(ReceiverBuilder.builder()
                        .receiveAddress("weiguanxiong@zju.edu.cn")
                        .build())
                .build();
        worker.sendEmail(email);
        Thread.sleep(1000);
    }
}
