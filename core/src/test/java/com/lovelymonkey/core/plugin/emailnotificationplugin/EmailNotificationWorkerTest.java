package com.lovelymonkey.core.plugin.emailnotificationplugin;

import java.lang.reflect.Field;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.lovelymonkey.core.functional.TestBase;
import com.lovelymonkey.core.plugin.emailnotificationplugin.builder.EmailBuilder;
import com.lovelymonkey.core.plugin.emailnotificationplugin.builder.ReceiverBuilder;

public class EmailNotificationWorkerTest extends TestBase{

    @Autowired
    private EmailNotificationWorker worker;

    @Autowired
    private JavaMailSenderImpl realSender;

    private GreenMail mailServer;

    private static final String USERNAME = "TestUser";
    private static final String PASSWORD = "PassWord";
    private static final String HOST = "localhost";

    @org.testng.annotations.BeforeClass
    public void BeforeMethod() {
        this.mailServer = new GreenMail();
        mailServer.setUser(USERNAME, PASSWORD);
        realSender.setUsername(USERNAME);
        realSender.setPassword(PASSWORD);
        realSender.setHost(HOST);
        realSender.setPort(ServerSetupTest.SMTP.getPort());
        mailServer.start();
    }

    @Test
    public void testWorkerInitiateHappyCase() {
        Assert.assertNotNull(worker);
        Assert.assertNotNull(realSender);
        Assert.assertEquals(realSender.getHost(), HOST);
        Assert.assertEquals(realSender.getPort(), ServerSetupTest.SMTP.getPort());
        Assert.assertEquals(realSender.getDefaultEncoding(), "UTF-8");
        Assert.assertEquals(realSender.getProtocol(), ServerSetupTest.SMTP.getProtocol());
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
        MimeMessage[] messages = mailServer.getReceivedMessages();
        Assert.assertEquals(1, messages.length);
        Assert.assertEquals(1, worker.getSuccessRequests().get());
    }

    @org.testng.annotations.AfterClass
    public void AfterClass() {
        mailServer.stop();
    }
}
