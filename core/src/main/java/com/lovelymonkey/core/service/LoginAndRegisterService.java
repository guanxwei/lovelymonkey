package com.lovelymonkey.core.service;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.SQLQueryConstant;

/**
 * LoginAndRegisterService.
 * @author guanxwei
 *
 */
@Slf4j
public class LoginAndRegisterService {

    @SuppressWarnings("rawtypes")
    @Setter @Getter
    private UserDao userDaoImp;

    /**
     * Check if the user exists in our DB.
     * @param u User entity
     * @return Check result.
     */
    public boolean isUserExist(final User u) {
        try {
            User real = (User) userDaoImp.getUserByUserNameAndPSD(u.getUserName(), u.getPassWord());
            return real != null;
        } catch (Exception e) {
            log.error(String.format("Failed to get the User info for username: [%]", u.getUserName()));
            throw new RuntimeException(e);
        }
    }

    /**
     * Update or Save the user info.
     * @param u User entity.
     */
    @SuppressWarnings("unchecked")
    public void updateOrSaveUser(final User u) {
        try {
            userDaoImp.updateOrSaveEntity(u);
        } catch (Exception e) {
            log.error(String.format("Failed to update or save User info for username : [%]", u.getUserName()));
            throw new RuntimeException(e);
        }
    }

    /**
     * Check if the username has been used for other users.
     * @param userName The username.
     * @return Check result.
     */
    public boolean isUserNameUsed(final String userName) {
        try {
            int count = userDaoImp.count(SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_USERNAME, userName);
            return count == 0 ? false : true;
        } catch (Exception e) {
            log.error(String.format("Failed to query userinfo for username: [%s]", userName));
            throw new RuntimeException(e);
        }
    }

    /**
     * Fetch user info by username and password.
     * @param userName The username.
     * @param accountPSD The password.
     * @return The user entity.
     */
    public User getUserByUserNameAndPSD(final String userName, final String accountPSD) {
        try {
            User u = (User) userDaoImp.getUserByUserNameAndPSD(userName, accountPSD);
            return u;
        } catch (Exception e) {
            log.error(String.format("Failed to get user info for username: [%s], PSD: [%S]", userName, accountPSD));
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete the user entity in DB.
     * @param u User entity.
     */
    @SuppressWarnings("unchecked")
    public void deleteUser(@NonNull final User u) {
        try {
            userDaoImp.deleteEntity(u);
        } catch (Exception e) {
           log.error(String.format("Failed to delete user for userName: [%s]", u.getUserName()));
           throw new RuntimeException(e);
        }
    }

}
