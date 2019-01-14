package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static models.ApplicationConstants.Roles.GUEST_LOGIN;
import static models.ApplicationConstants.UserAttributes.LOGIN_AS_USERNAME;

public class ServletUtils {

    //get user login from session
    public static String getUserLogin(HttpSession session) {
        String login = GUEST_LOGIN;
        Object attribute = session.getAttribute(LOGIN_AS_USERNAME);
        if (attribute != null) {
            login = (String) attribute;  //get login from session
        }
        return login;
    }

    //get current session and put there login to use in other jsp
    public static void setUserLogin(HttpSession session, String login) {
        session.setAttribute(LOGIN_AS_USERNAME, login);
    }

    //check if there is a login user in session
    private static boolean hasUserLogin(HttpSession session) {
        Object attribute = session.getAttribute(LOGIN_AS_USERNAME);
        return attribute != null;
    }

    //if there is a login user in session, remove it (logout)
    public static void tryLogout(HttpServletRequest request) {
        if (ServletUtils.hasUserLogin(request.getSession())) {
            request.getSession().removeAttribute(LOGIN_AS_USERNAME);
            request.removeAttribute(LOGIN_AS_USERNAME);
        }
    }
}
