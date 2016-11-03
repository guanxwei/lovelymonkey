package com.lovelymonkey.core.utils.constants.sql;

/**
 * Class that is used to store the query string about user info.
 * @author guanxwei
 *
 */
public final class UserInfoQuery {

    private UserInfoQuery() { }

    /**
     * Query string that is used to query a user by username and password.
     */
    public static final String QUERY_USER_BY_USERNAME_AND_PSD = "from User u where u.userName = ? and u.passWord = ?";

    /**
     * Query string that is used to query a user by username.
     */
    public static final String QUERY_USER_BY_USERNAME = "select count(1) from model_user where username = ?";

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
