package com.lovelymonkey.core.plugin.emailnotificationplugin;

import javax.mail.Multipart;

import lombok.Data;

/**
 * Email entity. Currently the plug-in only support MIME type email.
 * @author guanxwei
 *
 */
@Data
public class Email {
    //Currently the system will use the default sender info, managed by the configuration file.
    private Sender sender;
    private Receiver receiver;
    private String subject;
    private Multipart content;
}
