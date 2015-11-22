package com.lovelymonkey.core.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.RequestHandleConstant;

@Controller
@RequestMapping("/user")
@Slf4j
public class LoginAndRegisterController {

    @Setter
    private LoginAndRegisterService loginAndRegisterService;

    /**
     * 
     */
    @RequestMapping(value="/doLogin.htm", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doLogin(@RequestParam @NonNull final User u, final HttpSession httpSession) {
        String now = Calendar.getInstance().toString();

        log.info(String.format("User: [%s] login system at time [%s]",
                u.getUserName(), now));

        boolean isUserExist = loginAndRegisterService.isUserExist(u);

        if (isUserExist) {
            /* The User info provided by login matches record in our storage. */
            httpSession.setAttribute(RequestHandleConstant.LoginStatus.CURRENT_USER, u);
        } else {
            /* The User provide wrong userName or password. */
            httpSession.setAttribute(RequestHandleConstant.LoginStatus.LOGIN_FAIL_TIME, 1);
            return RequestHandleConstant.LoginStatus.LOGIN_SYSTEM_FAILED;
        }

        return RequestHandleConstant.LoginStatus.LOGIN_SYSTEM_SUCCESS;
    }

    @RequestMapping(value="/doRegister.htm", method={RequestMethod.POST})
    public String doRegister(@RequestParam @NonNull final User u) {
        log.info(String.format("Risgister user for userinfo [%s]", u.getUserName()));

        loginAndRegisterService.updateOrSaveUser(u);

        return RequestHandleConstant.LoginStatus.REGISTER_SYSTEM_SUCCESS;
    }

    @RequestMapping(value="/judge.htm", method={RequestMethod.GET})
    @ResponseBody
    public String isUserNameUsed(@RequestParam @NonNull final String userName) {
        log.info(String.format("Judge if username: [%s] has been used", userName));

        boolean isUserNameUsed = loginAndRegisterService.isUserNameUsed(userName);

        return Boolean.toString(isUserNameUsed);
    }
}
