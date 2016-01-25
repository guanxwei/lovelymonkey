package com.lovelymonkey.core.plugin.emailnotificationplugin;

import java.lang.reflect.Field;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lovelymonkey.core.functional.TestBase;

public class EmailNotificationWorkerTest extends TestBase{

    @Autowired
    private EmailNotificationWorker worker;

    @Autowired
    private JavaMailSenderImpl realSender;

    @BeforeClass
    public void BeforeClass() {
        //this.worker = new EmailNotificationWorker();
    }

    @Test
    public void testWorkerInitiateHappyCase() {
        Assert.assertNotNull(worker);
        Assert.assertNotNull(realSender);
        Assert.assertEquals(realSender.getHost(), "smtp.qq.com");
        Assert.assertEquals(realSender.getPort(), 465);
        Assert.assertEquals(realSender.getDefaultEncoding(), "UTF-8");
        Assert.assertEquals(realSender.getProtocol(), "smtp");
        Assert.assertEquals(realSender.getUsername(), "1026189878");
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
    public void testSendEmail() {
        Email email = new Email();
        email.setSubject("Test");
        Receiver receiver = new Receiver();
        receiver.setReceiverAddress("weiguanxiong@zju.edu.cn");
    }
}
