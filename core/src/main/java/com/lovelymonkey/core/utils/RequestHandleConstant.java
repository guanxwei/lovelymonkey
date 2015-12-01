package com.lovelymonkey.core.utils;

/**
 * Class that is used to store the returned response page url and other related info, all the url will be mapped
 * to a jsp file in the webapp/WEB-INF/jsp folder.
 * @author guanxwei
 *
 */
public class RequestHandleConstant {

    /**
     * Class that is used to store user management subsystem response status code.
     * @author guanxwei
     *
     */
    public static class UserManageStatus {

        /**
         * Login fail time counter.
         */
        public static final String LOGIN_FAIL_TIME = "fail_time";

        /**
         * The url user will visit if the customer login successfully.
         */
        public static final String LOGIN_SYSTEM_SUCCESS = "";

        /**
         * The url user will visit if the customer inout the wrong user info.
         */
        public static final String LOGIN_SYSTEM_FAILED = "";

        /**
         * The url user will visit if the customer register in our system successfully.
         */
        public static final String REGISTER_SYSTEM_SUCCESS = "";

        /**
         * The url user will visit if the customer fail to register in our system.
         */
        public static final String REGISTER_SYSTEM_FAILED = "";

        /**
         * Current user mapping to a specific customer(via sessionID), which will be stored in session managed by server.
         */
        public static final String CURRENT_USER = "current_user";
    }

}
