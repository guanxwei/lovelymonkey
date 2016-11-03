package com.lovelymonkey.core.utils.constants.sql;

/**
 * Class that is used to store the query string about message info.
 * @author guanxwei
 *
 */
public final class MessageInfoQuery {

    private MessageInfoQuery() { }

    /**
     * Query unread messages by user id.
     */
    public static final String QUERY_UNREAD_MESSAGES_BY_USER = "select * from model_message where receiverid = ? and status = 0";
}
