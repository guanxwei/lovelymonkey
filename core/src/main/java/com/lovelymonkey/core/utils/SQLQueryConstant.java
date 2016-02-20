package com.lovelymonkey.core.utils;

/**
 * This class is used to store the query strings for different service object,
 * all the query string should be maintained here. To avoid SQL injection attack
 * we must follow the pattern settled here.
 * @author guanxwei
 */
public final class SQLQueryConstant {

    /**
     * Class that is used to store the query string about user info.
     * @author guanxwei
     *
     */
    public static class UserInfoQuery {

        /**
         * Query string that is used to query a user by username and password.
         */
        public static final String QUERY_USER_BY_USERNAME_AND_PSD = "from User u where u.userName = ? and u.passWord = ?";

        /**
         * Query string that is used to query a user by username.
         */
        public static final String QUERY_USER_BY_USERNAME = "from User u where u.userName = ?";

        /**
         * Query string that is used to query a user by user entity ID.
         */
        public static final String QUERY_USER_BY_ID = "from User u where u.ID = ?";

        /**
         * Query string that is used to delete a user by user name.
         */
        public static final String DELETE_USER_BY_USERNAME = "delete User u where u.userName = ?";

        /**
         * Query string that is used to query a user by email.
         */
        public static final String QUERY_USER_BY_EMAIL = "from User u where u.email = ?";
    }

    /**
     * Class that is used to store the query string about menu info.
     * @author guanxwei
     *
     */
    public static class MenuInfoQuery {

        /**
         * Query string that is used to query the menu list by user level.
         */
        public static final String QUERY_MENE_BY_USER_LEVEL = "from Menu m where u.level <= ?";
    }
}
