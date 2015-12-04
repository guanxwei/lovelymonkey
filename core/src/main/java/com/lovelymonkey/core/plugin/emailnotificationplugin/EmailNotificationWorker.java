package com.lovelymonkey.core.plugin.emailnotificationplugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * Real email sender, the worker will collect the email and tempolary store the emails in its block queue,
 * and will initiate some thread to help to send the emails.
 * @author guanxwei
 *
 */
@Slf4j
public class EmailNotificationWorker {

    private static final BlockingQueue<Email> EMAIL_QUEUE = new LinkedBlockingQueue<Email>(100);
    private static final int DEFAULT_WORKERS = 5;

    static {
        InputStream in = EmailNotificationWorker.class.getResourceAsStream("/account.properties");
        Properties properties = new Properties();
        try {
            log.info("Try to load sender accoutn properties info");

            properties.load(in);

            log.info("Load properties finished with no error");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.info("Load properties finished with error :", e);
        }
    }
    /**
     * Defaul constructor.
     */
    public EmailNotificationWorker() {
        enact();
    }

    /**
     * Iniate some threads to get ready to work to send email.
     */
    private void enact() {
        for (int i = 0; i < DEFAULT_WORKERS; i++) {
            Thread t = new Thread(new Worker());
            t.start();
        }
    }

    /**
     * Put the email to the tail of email queue, wait to be sent out the receiver.
     * @param email Email entity.
     * @throws InterruptedException Exception.
     */
    public static void sendEmail(final Email email) throws InterruptedException {
        EMAIL_QUEUE.put(email);
    }

    /**
     * Inner class, do the real work to send the emails.
     * @author guanxwei
     *
     */
    static class Worker implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            log.info(String.format("Thread: [%s] begins to work", Thread.currentThread().getName()));

            while (true) {
                Email email;
                try {
                    email = EMAIL_QUEUE.take();
                    if (email == null) {
                        log.error("Something wrong happened, null is not acceptqble");
                    }

                    sendEmail(email);
                } catch (InterruptedException e) {
                    log.error(String.format("Error happened when the worker try to take email from the email queue, for detail: [%s] ", e));
                }
            }
        }

        private boolean sendEmail(final Email email) {

            return false;
        }
    }

}
