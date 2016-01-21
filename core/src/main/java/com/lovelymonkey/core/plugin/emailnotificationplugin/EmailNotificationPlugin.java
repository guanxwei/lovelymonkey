package com.lovelymonkey.core.plugin.emailnotificationplugin;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.plugin.Anything;
import com.lovelymonkey.core.plugin.Plugin;

/**
 * Plug-in can be used to send email to any mail server for user.
 * @author guanxwei
 *
 */
@Slf4j
public class EmailNotificationPlugin implements Plugin {

    private static final String PLUGIN_NAME = "emailnotificationplugin";

    @Autowired
    private EmailNotificationWorker worker;

    @Override
    public Anything serve(final Anything input) {
        Email email = (Email) input.getValue();
        Anything output = new Anything();

        try {
            log.info(String.format("Now we sent email for user: [%s]", email.getReceiver().getReceiverAddress()));

            worker.sendEmail(email);
            output.setValue(new String("Y"));

            log.info("Successfully add the email to the email queue");
        } catch (InterruptedException e) {
            output.setValue("N");
            log.error("Something wrong happened when it tried to add the email: [%s] to the email queue, the root cause is [%s]", email.toString(), e);
        }

        return output;
    }

    @Override
    public String getSymbol() {
        return PLUGIN_NAME;
    }

    @Override
    public boolean isRetriable() {
        return true;
    }

}
