package com.lovelymonkey.core.plugin.message;

import lombok.Data;

import com.lovelymonkey.core.model.User;

/**
 * Message object.
 * @author guanxwei
 *
 */
@Data
public class Message {

    private User sender;
    private User receiver;
    private String messageBody;
}
