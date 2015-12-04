package com.lovelymonkey.core.plugin.emailnotificationplugin;

import lombok.Data;

/**
 * Email entity.
 * @author guanxwei
 *
 */
@Data
public class Email {
    private Sender sender;
    private Receiver receiver;
}
