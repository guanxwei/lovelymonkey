package com.lovelymonkey.core.dao.impl;


import org.hibernate.Query;

import com.lovelymonkey.core.dao.UserDao;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.SQLQueryConstant;


@SuppressWarnings("hiding")
public class UserDaoImp<User> extends BaseDaoImp<User> implements UserDao<User>{

    /**
     * 
     * @param queryString PrepareStatement style query string used to query a specific user by UserName and PSD.
     * @param userName The userName.
     * @param PSD The password.
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByUserNameAndPSD(final String userName, final String PSD) {
        Query query = getCurrentThreadSession().createQuery(SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_USERNAME_AND_PSD);
        query.setString(0, userName);
        query.setString(1, PSD);

        return  (User) query.uniqueResult();
    }

}
