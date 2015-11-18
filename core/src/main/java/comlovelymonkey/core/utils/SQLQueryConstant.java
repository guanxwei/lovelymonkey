package com.lovelymonkey.core.utils;

/**
 * 
 * @author guanxwei
 * This class is used to store the query strings for different service object
 * all the query string should be maintained here. To avoid SQL injection attack
 * we must follow the pattern setted here.
 */
public final class SQLQueryConstant {

    public static class UserInfoQuery {

        public static final String QUERY_USER_BY_USERNAME_AND_PSD = "from User u where u.userName = ? and u.passWord = ?";

        public static final String QUERY_USER_BY_USERNAME = "from User u where u.userName = ?";

        public static final String QUERY_USER_BY_ID = "from User u where u.userName = ?";

    }

}
