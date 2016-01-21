package com.lovelymonkey.core.plugin.emailnotificationplugin;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Email sender.
 * @author guanxwei
 *
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Sender extends Authenticator {

    private final String senderAddress;

    private final String senderPsd;

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(senderAddress, senderPsd);
    }

}
