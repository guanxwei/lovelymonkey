package com.lovelymonkey.core.service;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.PasswordResetRecord;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.constants.sql.UserInfoQuery;

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
            int count = userDaoImp.count(UserInfoQuery.QUERY_USER_BY_USERNAME, userName);
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

    /**
     * Delete the user by user name.
     * @param userName user name.
     */
    public void deleteUserByUserName(final String userName) {
        try {
            userDaoImp.deleteUserByUserName(userName);
        } catch (Exception e) {
            log.error(String.format("Failed to delelte user : [%s]", userName));
            throw new RuntimeException(e);
        }
    }

    /**
     * Get user list by input query string.
     * @param queryString query string.
     * @param params specific parameters.
     * @return user list.
     */
    public List<User> getUserList(final String queryString, final String[] params) {
        try {
            @SuppressWarnings("unchecked")
            List<User> result = userDaoImp.list(queryString, params);
            return result;
        } catch (Exception e) {
            log.error(String.format("Failed to fetch user list for query string : [%s]", queryString));
            throw new RuntimeException(e);
        }
    }

    /**
     * Get specific user entity by email.
     * @param email The email.
     * @return The user.
     */
    public User getUserByEmail(final String email) {
        try {
            User user = (User) userDaoImp.getUserByEmail(email);
            return user;
        } catch (Exception e) {
            log.error(String.format("Failed to fetch user : [%s]", email));
            throw new RuntimeException(e);
        }
    }

    /**
     * Insert a record for a password reset request.
     * @param record single record information for a password reset request.
     */
    @SuppressWarnings("unchecked")
    public void record(@NonNull final PasswordResetRecord record) {
        try {
            userDaoImp.updateOrSaveEntity(record);
        } catch (Exception e) {
            log.error("Failed to insert a record for userid [{}]", record.getUserId());
        }
    }
}
