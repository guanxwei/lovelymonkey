package com.lovelymonkey.core.utils;

/**
 * Utility class to generate email template for purposes.
 * @author guanxwei
 *
 */
public final class EmailTemplateUtils {

    private EmailTemplateUtils() { }

    /**
     * Used to generate password reset email body based on the userName and uuid sent in.
     * @param userName the userName representing who wants to reset the password.
     * @param uuid Random generated string to identify the password request, our system will use it to
     * verify the legitimization of the request when customer use the generated url to reset their password.
     * @return email body.
     */
    public static String generatePasswordResetEmail(final String userName, final String uuid) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div> "
                + "<div><p>Dear " + userName + "</p>"
                + "<div> You need to reset your password to login our system, please click here: "
                    + "<a href='http://localhost:8080/core/reset.htm?userName=%s&uuid=%s target='_blank'><br/>"
                    + "or you can copy the link below to your web brower to reset your password:<br/>"
                    + "http://localhost:8080/core/reset.htm?userName=%s&uuid=%s"
                + "/div>"
                + "</div>");
        return String.format(sb.toString(), userName, uuid, userName, uuid);
    }
}
