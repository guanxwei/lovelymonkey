package com.lovelymonkey.core.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.ControllerConstant;
import com.lovelymonkey.core.utils.RequestHandleConstant;

/**
 * User info management controller.
 * @author guanxwei
 *
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class LoginAndRegisterController {

    @Autowired @Setter
    private LoginAndRegisterService loginAndRegisterService;

    /**
     * Login method that is used to help user login to our system.
     * @param u User entity input by customer.
     * @param httpSession Session maps to a specific user.
     * @return The correct page that user should visit determined by the input info provided by customer.
     */
    @RequestMapping(value = "/doLogin.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doLogin(@RequestParam @NonNull final User u, final HttpSession httpSession) {
        String now = Calendar.getInstance().toString();

        log.info(String.format("User: [%s] login system at time [%s]",
                u.getUserName(), now));

        boolean isUserExist = loginAndRegisterService.isUserExist(u);

        if (isUserExist) {
            /* The User info provided by login matches record in our storage. */
            httpSession.setAttribute(RequestHandleConstant.UserManageStatus.CURRENT_USER, u);
        } else {
            /* The User provide wrong userName or password. */
            httpSession.setAttribute(RequestHandleConstant.UserManageStatus.LOGIN_FAIL_TIME, 1);
            return RequestHandleConstant.UserManageStatus.LOGIN_SYSTEM_FAILED;
        }

        return RequestHandleConstant.UserManageStatus.LOGIN_SYSTEM_SUCCESS;
    }

    /**
     * Register method that helps customer register account in our system.
     * @param u User entity input by customer.
     * @param session HttpSession used to store customer specific info.
     * @return The correct page that user should visit determined by the input info provided by customer.
     */
    @RequestMapping(value = "/doRegister.htm", method = {RequestMethod.POST})
    public String doRegister(final User u, final HttpSession session) {
        log.info(String.format("Risgister user for userinfo [%s]", u.getUserName()));

        System.out.println("helloworld" + u);
        loginAndRegisterService.updateOrSaveUser(u);
        session.setAttribute(ControllerConstant.LoginAndRegisterControlerConstants.CURRENT_USER,
                loginAndRegisterService.getUserByUserNameAndPSD(u.getUserName(), u.getPassWord()));

        return RequestHandleConstant.UserManageStatus.REGISTER_SYSTEM_SUCCESS;
    }

    /**
     * UserName check method that is used to guarantee the customer input a unique username so that
     * duplicate username will not happen.
     * @param userName The username that is typed in by the customer in the front page.
     * @return The correct page that user should visit determined by the input info provided by customer.
     */
    @RequestMapping(value = "/judge.htm", method = {RequestMethod.GET})
    @ResponseBody
    public String isUserNameUsed(@RequestParam @NonNull final String userName) {
        log.info(String.format("Judge if username: [%s] has been used", userName));

        boolean isUserNameUsed = loginAndRegisterService.isUserNameUsed(userName);

        return Boolean.toString(isUserNameUsed);
    }
}
