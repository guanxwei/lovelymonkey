package com.lovelymonkey.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * DashBoard controller.
 * @author guanxwei
 *
 */
@Controller(value = "/dashboard")
public class DashBoardController {

    /**
     * Load user level specific contents. Will first check if the customer has login in the system or not.
     * If yes, will load the content according to the customer's user level. If not will load the default
     * contents that are open to visitors.
     * @param userId userId.
     * @return Dashboard jsp page.
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/{userId}")
    public String showDashBoard(@PathVariable final String userId) {
        return null;
    }
}
