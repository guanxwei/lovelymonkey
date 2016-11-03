package com.lovelymonkey.core.activity.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.stream.core.component.Activity;
import org.stream.core.component.ActivityResult;
import org.stream.core.execution.WorkFlowContext;
import org.stream.core.resource.Resource;

import com.lovelymonkey.core.controller.DashBoardController;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.MessageService;

import lombok.extern.slf4j.Slf4j;

/**
 * Internal activity executed by stream workflow {@link Engine} instances.
 * Mainly used to set up customer specific navigator bar contents.
 * @author guanxwei
 *
 */
@Slf4j
public class LoadNavigatorBarActivity extends Activity {

    private static final String DEFAULT_ANONYMOUS_HEAD_PORTRAIT = "default.jpg";

    @Autowired
    private MessageService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityResult act() {
        Resource primaryResource = WorkFlowContext.getPrimary();
        ModelMap map = (ModelMap) primaryResource.getValue();
        User user = (User) map.get(DashBoardController.CURRENT_USER);
        boolean isVisitor = DashBoardController.VISITOR_NAME.equals(user.getUserName());
        if (isVisitor) {
            log.info("Anonymous visitor. Show default anonymous head portrait to the visitor");
            user.setEmojiPath(DEFAULT_ANONYMOUS_HEAD_PORTRAIT);
            map.put("isVisitor", false);
        }
        if (!isVisitor) {
            int unreadMessages = service.getUnreadMessageNumber(user);
            map.put("messages", unreadMessages);
        }

        return ActivityResult.SUCCESS;
    }

}
