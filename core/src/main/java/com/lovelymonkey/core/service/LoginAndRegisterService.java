package com.lovelymonkey.core.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.SQLQueryConstant;

@Slf4j
public class LoginAndRegisterService {

    @SuppressWarnings("rawtypes")
    @Setter
    private UserDao userDaoImp;

    public boolean isUserExist(User u) {
        try{
            User real = (User) userDaoImp.getUserByUserNameAndPSD(u.getUserName(), u.getPassWord());
            return real != null;
        } catch (Exception e) {
            log.error(String.format("Failed to get the User info for username: [%]", u.getUserName()));
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

    public boolean isUserNameUsed(final String userName) {
        try {
            int count = userDaoImp.count(SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_USERNAME, userName);
            return count == 0 ? false:true;
        } catch (Exception e) {
            log.error(String.format("Failed to query userinfo for username: [%]", userName));
            throw new RuntimeException(e);
        }
    }
}
