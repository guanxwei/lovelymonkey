package com.lovelymonkey.core.plugin.emailnotificationplugin;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Email sender.
 * @author guanxwei
 *
 */
@AllArgsConstructor
@Data
public class Sender {

    private final String senderAddress;

    private final String senderPsd;

}
