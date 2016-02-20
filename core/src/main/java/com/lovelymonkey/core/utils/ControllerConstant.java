package com.lovelymonkey.core.utils;

/**
 * Class provide constants used by controllers.
 * @author wgx
 *
 */
public class ControllerConstant {

    /**
     * Constants used in LoginAndRegisterControler.
     */
    public static class LoginAndRegisterControlerConstants {

        /**
         * Current user info represents a specific customer.
         */
        public static final String CURRENT_USER = "currentUser";

        /**
         * The title of mail sent to customer for password reset request.
         */
        public static final String PASS_WORD_RESET_SUBJECT = "Password reset";

        /**
         * Response to customer to indicate that the email customer typed in does not exist in the syste.
         */
        public static final String EMAIL_NOT_EXISTED = "emailnotexisted";
    }

}
