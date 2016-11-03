package com.lovelymonkey.core.dao.impl;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.constants.sql.UserInfoQuery;

import org.hibernate.Query;

/**
 * Provide User info management specific Dao functions.
 * @author guanxwei
 *
 * @param <User>
 */
@SuppressWarnings("hiding")
public class UserDaoImp<User> extends BaseDaoImp<User> implements UserDao<User> {

    /**
     * User info management specific function, which is used to fetch the user entity determined by the username and password.
     * @param userName The userName.
     * @param accountPSD The password.
     * @return The user entity determined by username and password.
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUserNameAndPSD(final String userName, final String accountPSD) {
        Query query = getCurrentThreadSession().createQuery(UserInfoQuery.QUERY_USER_BY_USERNAME_AND_PSD);
        query.setString(0, userName);
        query.setString(1, accountPSD);

        return  (User) query.uniqueResult();
    }

    /**
     * User info management specific function, which is used to delete the user by user name.
     * @param userName user name.
     */
    @Override
    public void deleteUserByUserName(final String userName) {
        Query query = getCurrentThreadSession().createQuery(UserInfoQuery.DELETE_USER_BY_USERNAME);
        query.setString(0, userName);

        query.executeUpdate();
    }

    /**
     * User info management specific function, which is used to fetch the user info by the email.
     * @param email The email.
     * @return The user entity.
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByEmail(final String email) {
        Query query = getCurrentThreadSession().createQuery(UserInfoQuery.QUERY_USER_BY_EMAIL);
        query.setString(0, email);

        return (User) query.uniqueResult();
    }
}
