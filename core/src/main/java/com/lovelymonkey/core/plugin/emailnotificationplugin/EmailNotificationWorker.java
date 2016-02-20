package com.lovelymonkey.core.plugin.emailnotificationplugin;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Real email sender, the worker will collect the email and temporary store the mails in its block queue, then daemon workers will
 * send the mails to customers one by one at some time.
 * @author guanxwei
 *
 */
@Slf4j
public class EmailNotificationWorker {

    private static final int DEFAULT_WORKERS = 5;
    private static final int RETRY_TIMES = 3;
    private static final BlockingQueue<Email> EMAIL_QUEUE = new LinkedBlockingQueue<Email>(100);
    private static final Thread[] THREADS = new Thread[DEFAULT_WORKERS];

    @Autowired
    @Setter
    private JavaMailSenderImpl realSender;

    /**
     * Default constructor.
     */
    public EmailNotificationWorker() {
        enact();
    }

    /**
     * Initiate some threads to get ready to work to send email.
     */
    private void enact() {
        for (int i = 0; i < DEFAULT_WORKERS; i++) {
            THREADS[i] = new Thread(new Worker());
            log.info("Initiate worker thread to prepare to send emails");
            THREADS[i].setName("EmailSender" + i);
            THREADS[i].start();
        }
    }

    /**
     * Put the email to the tail of email queue, wait to be sent out the receiver.
     * @param email Email entity.
     * @throws InterruptedException Exception.
     */
    public void sendEmail(final Email email) throws InterruptedException {
        EMAIL_QUEUE.put(email);
    }

    /**
     * Inner class, do the real work to send the mails.
     * @author guanxwei
     *
     */
    class Worker implements Runnable {

        @Override
        public void run() {
            log.info(String.format("Thread: [%s] begins to work", Thread.currentThread().getName()));

            while (true) {
                Email email = null;
                int count = 0;
                try {
                    email = EMAIL_QUEUE.take();
                    if (email == null) {
                        log.error("Something wrong happened, null is not acceptable");
                        continue;
                    }
                    sendEmail(email);
                    log.info("Successfully sent email to customer");
                } catch (Exception e) {
                    log.error(String.format("Error happened when the worker try to take email from the email queue, for detail: [%s] ", e));

                    /**
                     * Will try to send the email again for at most <code>RETRY_TIMES</code> times.
                     */
                    if (count++ < RETRY_TIMES) {
                        try {
                            sendEmail(email);
                        } catch (Exception e1) {
                            log.error("Fail ggain, the {}th times", count + 1);
                        }
                    }
                }
            }
        }

        private void sendEmail(final Email email) throws Exception {
            MimeMessage message = realSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //Currently we use the default email sender 'realSender'.
            if (email.getSender() == null) {
                helper.setFrom(new InternetAddress(realSender.getUsername()));
            } else {
                helper.setFrom(new InternetAddress(email.getSender().getSenderAddress()));
            }
            helper.setSubject(email.getSubject());
            helper.setSentDate(new Date());
            helper.setTo(new InternetAddress(email.getReceiver().getReceiverAddress()));
            helper.setText(email.getContent(), true);
            realSender.send(message);
        }
    }

}
