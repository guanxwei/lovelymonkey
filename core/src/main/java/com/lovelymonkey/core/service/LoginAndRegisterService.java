package com.lovelymonkey.core.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.User;

@Slf4j
public class LoginAndRegisterService {

    @SuppressWarnings("rawtypes")
    @Setter
    private UserDao userDaoImp;

    public User getUserByUserNameAndPSD(final String userName, final String PSD) {
        try{
            return (User) userDaoImp.getUserByUserNameAndPSD(userName, PSD);
        } catch (Exception e) {
            log.error(String.format("Failed to get the User info for username: [%]", userName));
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public void updateOrSaveUser(final User u) {
        try {
            userDaoImp.updateOrSaveEntity(u);
        } catch (Exception e) {
            log.error(String.format("Failed to update or save User info for username : [%]", u.getUserName()));
            throw new RuntimeException(e);
        }
    }

    public boolean isUserNameUsed(final String hql) {
        try {
            return userDaoImp.count(hql) == 0 ? true:false;
        } catch (Exception e) {
            log.error(String.format("Failed to query userinfo for query string: [%]", hql));
            throw new RuntimeException(e);
        }
    }
}
