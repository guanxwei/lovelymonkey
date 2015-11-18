package com.lovelymonkey.core.dao;

public interface UserDao<User> extends BaseDao<User>{

    public User getUserByUserNameAndPSD(final String userName, final String PSD);

}
