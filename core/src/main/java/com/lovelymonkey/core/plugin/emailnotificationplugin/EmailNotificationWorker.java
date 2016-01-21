package com.lovelymonkey.core.plugin.emailnotificationplugin;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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

    private static final BlockingQueue<Email> EMAIL_QUEUE = new LinkedBlockingQueue<Email>(100);
    private static final int DEFAULT_WORKERS = 5;

    @Autowired
    @Setter
    private JavaMailSender realSender;

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
            Thread t = new Thread(new Worker());
            log.info("Initiate worker thread to prepare to send emails");
            t.setName("EmailSender" + i);
            t.start();
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
                Email email;
                try {
                    email = EMAIL_QUEUE.take();
                    if (email == null) {
                        log.error("Something wrong happened, null is not acceptable");
                    }

                    sendEmail(email);
                } catch (Exception e) {
                    log.error(String.format("Error happened when the worker try to take email from the email queue, for detail: [%s] ", e));
                }
            }
        }

        private void sendEmail(final Email email) throws Exception {
            MimeMessage message = realSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(email.getSender().getSenderAddress()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getReceiver().getReceiverAddress()));
            message.setSubject(email.getSubject());
            message.setSentDate(new Date());
            realSender.send(message);
        }
    }

}
