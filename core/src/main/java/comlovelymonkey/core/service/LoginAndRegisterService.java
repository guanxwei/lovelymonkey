package com.lovelymonkey.core.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.dao.impl.UserDaoImp;
import com.lovelymonkey.core.model.User;

@Slf4j
public class LoginAndRegisterService {

    @Setter
    private UserDaoImp userDaoImp;

    public User getUserByUserNameAndPSD(final String userName, final String PSD) {
        try{
            return (User) userDaoImp.getUserByUserNameAndPSD(userName, PSD);
        } catch (Exception e) {
            log.error(String.format("Failed to get the User info for username: [%]", userName));
            throw new RuntimeException(e);
        }
    }

    public void updateOrSaveUser(final User u) {
        try {
            userDaoImp.updateOrSaveEntity(u);
        } catch (Exception e) {
            log.error(String.format("Failed to update or save User info for username : [%]", u.getUserName()));
            throw new RuntimeException(e);
        }
    }

    public boolean isUserNameUsed(final String userName) {
        try {
            return userDaoImp.isUserNameUsed(userName);
        } catch (Exception e) {
            log.error(String.format("Failed to query userinfo for username : [%]", userName));
            throw new RuntimeException(e);
        }
    }
}
