package com.lovelymonkey.core.service;

import java.util.List;

import com.lovelymonkey.core.dao.BaseDao;
import com.lovelymonkey.core.model.Message;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.constants.sql.MessageInfoQuery;

import lombok.Data;
import lombok.Setter;

/**
 * Message service for message related generic requirement, like retrieve an message from the DB for a specific customer.
 * @author guanxwei
 *
 */
@Data
public class MessageService {

    @Setter
    private BaseDao<Message> baseDaoImpl;

    private static final int PAGE_SIZE = 10;

    /**
     * Fetch unread message list from the DB by user id and pageIdex. Default, system will fetch list with auto paging.
     * @param receiver message receiver.
     * @param pageIndex page index.
     * @return unread messages.
     */
    public List<Message> getMessagesUnread(final User receiver, final int pageIndex) {
        return baseDaoImpl.getListByPageIndex(pageIndex, PAGE_SIZE, MessageInfoQuery.QUERY_UNREAD_MESSAGES_BY_USER, receiver.getId());
    }

    /**
     * Fetch unread messages' number.
     * @param receiver message receiver.
     * @return unread messages number
     */
    public int getUnreadMessageNumber(final User receiver) {
        return baseDaoImpl.count(MessageInfoQuery.QUERY_UNREAD_MESSAGES_BY_USER, receiver.getId());
    }

}
