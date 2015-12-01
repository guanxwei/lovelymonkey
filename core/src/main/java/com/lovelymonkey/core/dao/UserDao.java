package com.lovelymonkey.core.dao;

/**
 * Interface define user info management specific functions.
 * @author guanxwei
 *
 * @param <User>
 */
public interface UserDao<User> extends BaseDao<User> {

    /**
     * Method that is used to fetch user info by username and password.
     * @param userName The username.
     * @param accountPSD The password.
     * @return The queried user entity.
     */
    User getUserByUserNameAndPSD(final String userName, final String accountPSD);

}
