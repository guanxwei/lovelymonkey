package com.lovelymonkey.core.dao.impl;

import java.math.BigInteger;

import org.hibernate.Query;

import com.lovelymonkey.core.dao.BaseDao;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.SQLQueryConstant;

public class UserDaoImp extends BaseDao {

    /**
     * @param queryString PrepareStatement style query string used to query a specific user by ID.
     * @param ID UserID here uniquely represents a user registered in our system
     */
    @Override
    public Object getObjectByID(final String ID) {
        // TODO Auto-generated method stub
        Query query = getCurrentThreadSession().createQuery(SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_ID);
        query.setString(0, ID);

        return query.uniqueResult();
    }

    /**
     * 
     * @param queryString PrepareStatement style query string used to query a specific user by UserName and PSD.
     * @param userName The userName.
     * @param PSD The password.
     * @return
     */
    public User getUserByUserNameAndPSD(final String userName, final String PSD) {
        Query query = getCurrentThreadSession().createQuery(SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_USERNAME_AND_PSD);
        query.setString(0, userName);
        query.setString(1, PSD);

        return (User) query.uniqueResult();
    }

    /**
     * 
     * @param userName The UserName input by customer.
     * @return
     */
    public boolean isUserNameUsed(String userName) {
        Query query = getCurrentThreadSession().createQuery("select count(*) " + 
                SQLQueryConstant.UserInfoQuery.QUERY_USER_BY_USERNAME);
        query.setString(0, userName);

        BigInteger bigVal =(BigInteger)query.list().get(0);

        return bigVal.intValue() != 0;
    }
}
