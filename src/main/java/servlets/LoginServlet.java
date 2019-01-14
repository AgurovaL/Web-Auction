package servlets;

import models.User;
import staticData.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static models.ApplicationConstants.ErrorsStrings.ERROR_MESSAGE;
import static models.ApplicationConstants.ErrorsStrings.INVALID_LOGIN_FORM;
import static models.ApplicationConstants.PagesAttributes.PAGE_FOLDER_PATH;
import static models.ApplicationConstants.PagesAttributes.PAGE_JSP_EXTENTION;
import static models.ApplicationConstants.PagesURLs.LOGIN_URL;
import static models.ApplicationConstants.PagesURLs.SHOW_ITEMS_URL;
import static models.ApplicationConstants.UserAttributes.LOGIN;
import static models.ApplicationConstants.UserAttributes.PASSWORD;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtils.tryLogout(request);
        request.getRequestDispatcher(PAGE_FOLDER_PATH + LOGIN_URL + PAGE_JSP_EXTENTION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        //get current session and put there login to use in other jsp
        ServletUtils.setUserLogin(request.getSession(), login);

        //check login and password
        boolean isRightUser = false;
        for (User user : UserList.usersList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                isRightUser = true;
            }
        }

        if (isRightUser) {
            response.sendRedirect(SHOW_ITEMS_URL);
        } else {
            request.setAttribute(ERROR_MESSAGE, INVALID_LOGIN_FORM);
            request.getRequestDispatcher(PAGE_FOLDER_PATH + LOGIN_URL + PAGE_JSP_EXTENTION).forward(request, response);
        }
    }
}
