package com.lovelymonkey.core.dao.impl;

import org.hibernate.Query;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.SQLQueryConstant;

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
        Query query = getCurrentThreadSession().createQuery(SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_USERNAME_AND_PSD);
        query.setString(0, userName);
        query.setString(1, accountPSD);

        return  (User) query.uniqueResult();
    }

}
