package com.lovelymonkey.core.plugin.emailnotificationplugin;

import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.plugin.Anything;
import com.lovelymonkey.core.plugin.Plugin;

/**
 * Plugin can be used to send email to any mail server for user.
 * @author guanxwei
 *
 */
@Slf4j
public class EmailNotificationPlugin implements Plugin {

    @Override
    public Anything serve(final Anything input) {
        Email email = (Email) input.getValue();
        try {
            log.info(String.format("Now we sent email for user: [%s]", email.getReceiver().getReceiverAddress()));

            EmailNotificationWorker.sendEmail(email);

            log.info("Successfully add the email to the email queue");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            log.error("Something wrong happened when it tried to add the email: [%s] to the email queue, the root cause is [%s]", email.toString(), e);
        }

        Anything output = new Anything();
        output.setValue(new String("Y"));

        return output;
    }

}
