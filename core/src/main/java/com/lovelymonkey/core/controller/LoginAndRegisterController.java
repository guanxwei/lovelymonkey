package com.lovelymonkey.core.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lovelymonkey.core.exceptions.LocaleException;
import com.lovelymonkey.core.model.PasswordResetRecord;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.plugin.Anything;
import com.lovelymonkey.core.plugin.Plugin;
import com.lovelymonkey.core.plugin.PluginException;
import com.lovelymonkey.core.plugin.PluginManager;
import com.lovelymonkey.core.plugin.emailnotificationplugin.Email;
import com.lovelymonkey.core.plugin.emailnotificationplugin.EmailNotificationPlugin;
import com.lovelymonkey.core.plugin.emailnotificationplugin.builder.EmailBuilder;
import com.lovelymonkey.core.plugin.emailnotificationplugin.builder.ReceiverBuilder;
import com.lovelymonkey.core.service.LoginAndRegisterService;
import com.lovelymonkey.core.utils.EmailTemplateUtils;
import com.lovelymonkey.core.utils.LocaleHelper;
import com.lovelymonkey.core.utils.RequestHandleConstant;
import com.lovelymonkey.core.utils.constants.controller.LoginAndRegisterControlerConstants;

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

    @Autowired
    private PluginManager pluginManager;

    private static final int RETRY_TIMES = 3;

    /**
     * Login method that is used to help user login to our system.
     * @param u User entity input by customer.
     * @param httpSession Session maps to a specific user.
     * @return The correct page that user should visit determined by the input info provided by customer.
     */
    @RequestMapping(value = "/doLogin.htm", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doLogin(final User u, final HttpSession httpSession) {
        String now = Calendar.getInstance().getTime().toString();

        log.info(String.format("User: [%s] login system at time [%s]",
                u.getUserName(), now));

        boolean isUserExist = loginAndRegisterService.isUserExist(u);

        if (isUserExist) {
            /* The User info provided by customer matches record in storage. */
            httpSession.setAttribute(RequestHandleConstant.UserManageStatus.CURRENT_USER, u);
        } else {
            /* The User provide wrong userName or password. */
            int fails = httpSession.getAttribute(RequestHandleConstant.UserManageStatus.LOGIN_FAIL_TIME) == null ? 0 : Integer.valueOf(
                    (String)httpSession.getAttribute(RequestHandleConstant.UserManageStatus.LOGIN_FAIL_TIME));
            httpSession.setAttribute(RequestHandleConstant.UserManageStatus.LOGIN_FAIL_TIME, fails++);

            return RequestHandleConstant.UserManageStatus.LOGIN_SYSTEM_FAILED + ":" + fails;
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
    @ResponseBody
    public String doRegister(final User u, final HttpSession session) {
        log.info(String.format("Risgister user for userinfo [%s]", u.getUserName()));

        /* Before registering, we still need to check if the userName has been used for other users. */
        log.info("Check if the user name [{}] has been used before", u.getUserName());

        boolean isUserNameUsed = loginAndRegisterService.isUserNameUsed(u.getUserName());
        if (isUserNameUsed) {
            log.info("Tried to register a account by used user name [{}]", u.getUserName());
            return RequestHandleConstant.UserManageStatus.REGISTER_SYSTEM_FAILED;
        }

        /* Default, customer level will be 1, normal customers */
        u.setLevel(1);
        loginAndRegisterService.updateOrSaveUser(u);
        session.setAttribute(LoginAndRegisterControlerConstants.CURRENT_USER,
                loginAndRegisterService.getUserByUserNameAndPSD(u.getUserName(), u.getPassWord()));

        return RequestHandleConstant.UserManageStatus.REGISTER_SYSTEM_SUCCESS;
    }

    /**
     * Method used to check if the username committed by current customer has been used or not.
     * @param userName The username that is typed in by the customer in the front page.
     * @return The result if the username has been used or not. The front-end page will use it to do the nest step stuffs.
     */
    @RequestMapping(value = "/judge.htm", method = {RequestMethod.GET})
    @ResponseBody
    public String isUserNameUsed(final String userName) {
        log.info(String.format("Judge if username: [%s] has been used", userName));

        boolean isUserNameUsed = loginAndRegisterService.isUserNameUsed(userName);

        return Boolean.toString(isUserNameUsed);
    }

    /**
     * Send the password reset link to the email address customer typed in.
     * @param email The email address of customer.
     * @return The customer specific password reset page.
     * @throws PluginException The exception.
     */
    @RequestMapping(value = "/passwordreset.htm", method = {RequestMethod.POST})
    @ResponseBody
    public String sendPasswordResetLink(final String email, final HttpServletRequest request) {
        log.info(String.format("Receive passWord reset request for :", email));

        User user = loginAndRegisterService.getUserByEmail(email);
        if (user == null) {
            return LoginAndRegisterControlerConstants.EMAIL_NOT_EXISTED;
        }
        Plugin emailSendPlugin = pluginManager.getPluginByName(EmailNotificationPlugin.PLUGIN_NAME);
        Anything link = new Anything();
        String uuid = UUID.randomUUID().toString();
        String emailBody = EmailTemplateUtils.generatePasswordResetEmail(user.getUserName(), uuid);
        Email mail = null;
        try {
            mail = EmailBuilder.builder()
                    .subject(LocaleHelper.translate(LoginAndRegisterControlerConstants.PASS_WORD_RESET_SUBJECT, request.getLocale()))
                    .receiver(ReceiverBuilder.builder()
                            .receiveAddress(user.getEmail())
                            .build())
                    .content(emailBody)
                    .build();
        } catch (LocaleException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        link.setValue(mail);
        int count = 0;
        try {
            emailSendPlugin.serve(link);
        } catch (PluginException e) {
            log.error("Failed to send email to customer, will retry if possible");
            boolean isOK = false;
            while (!isOK && emailSendPlugin.isRetriable() && count++ < RETRY_TIMES) {
                try {
                    emailSendPlugin.serve(link);
                    isOK = true;
                } catch (PluginException e1) {
                    log.error("Failed to send email to customer again [{}]th times", count + 1);
                }
            }
            return Boolean.FALSE.toString();
        }

        PasswordResetRecord record = new PasswordResetRecord();
        record.setRecordDate(Calendar.getInstance().getTime().toString());
        record.setSignature(uuid);
        record.setUserId(user.getId());
        loginAndRegisterService.record(record);

        return Boolean.TRUE.toString();
    }

    /**
     * Verify if the email has been used or not.
     * @param email The email address.
     * @return The verification result.
     */
    @RequestMapping(value = "/verifymail.htm", method = {RequestMethod.GET})
    @ResponseBody
    public String isEmailUsed(final String email) {
        log.info("Verify if email : [{}] has been used or not", email);

        User user = loginAndRegisterService.getUserByEmail(email);
        if (user == null) {
            return Boolean.FALSE.toString();
        } else {
            return Boolean.TRUE.toString();
        }
    }
}
