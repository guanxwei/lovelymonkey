package com.lovelymonkey.core.plugin.emailnotificationplugin.builder;

import com.lovelymonkey.core.plugin.emailnotificationplugin.Email;
import com.lovelymonkey.core.plugin.emailnotificationplugin.Receiver;
import com.lovelymonkey.core.plugin.emailnotificationplugin.Sender;

/**
 * Email builder.
 * @author guanxwei
 *
 */
public class EmailBuilder {

    private Sender sender;
    private Receiver receiver;
    private String subject;
    private String content;

    /**
     * Build Sender.
     * @param sender Sender.
     * @return EmailBuilder.
     */
    public EmailBuilder sender(final Sender sender) {
        this.sender = sender;
        return this;
    }

    /**
     * Build Receiver.
     * @param receiver Receiver.
     * @return EmailBuilder.
     */
    public EmailBuilder receiver(final Receiver receiver) {
        this.receiver = receiver;
        return this;
    }

    /**
     * Build subject.
     * @param subject Subject.
     * @return EmailBuilder.
     */
    public EmailBuilder subject(final String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * Build content.
     * @param content Content.
     * @return EmailBuilder.
     */
    public EmailBuilder content(final String content) {
        this.content = content;
        return this;
    }

    /**
     * Create new EmailBuilder.
     * @return EmailBuilder.
     */
    public static EmailBuilder builder() {
        return new EmailBuilder();
    }

    /**
     * Build Email from EmailBuilder.
     * @return Built Email.
     */
    public Email build() {
        Email email = new Email();
        email.setContent(this.content);
        email.setSender(this.sender);
        email.setSubject(this.subject);
        email.setReceiver(this.receiver);
        return email;
    }
}
