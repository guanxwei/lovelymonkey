package com.lovelymonkey.core.controller;

import javax.servlet.http.HttpSession;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.RequestHandleConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * System administration dashboard controller.
 */
@Slf4j
public class AdminConsoleController {

    /**
     * Handler to show dashboard content to the customer.
     * @param session HttpSesson holding current using customer's information.
     * @return Currently used dashboard view path.
     */
    public String showDashboard(final HttpSession session) {

        User currentUser = (User) session.getAttribute(RequestHandleConstant.UserManageStatus.CURRENT_USER);
        log.info("User [{}] login to system", currentUser.getUserName());

        return null;
    }
}
