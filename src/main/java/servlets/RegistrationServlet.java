package servlets;

import models.ApplicationConstants;
import models.User;
import service.UserDALService;
import service.impl.UserDALServiceImpl;
import staticData.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static models.ApplicationConstants.ErrorsStrings.*;
import static models.ApplicationConstants.PagesAttributes.PAGE_FOLDER_PATH;
import static models.ApplicationConstants.PagesAttributes.PAGE_JSP_EXTENTION;
import static models.ApplicationConstants.PagesURLs.REGISTRATION_URL;
import static models.ApplicationConstants.UserAttributes.*;

public class RegistrationServlet extends HttpServlet {
    private UserDALService userDALService = new UserDALServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(PAGE_FOLDER_PATH + REGISTRATION_URL + PAGE_JSP_EXTENTION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        //get current session and put there login to use in other jsp
        ServletUtils.setUserLogin(request.getSession(), login);

        //check login and password
        boolean isSuchUser = false;
        for (User user : UserList.usersList) {
            if (user.getLogin().equals(login) || user.getPassword().equals(password)) {
                isSuchUser = true;
            }
        }

        if (isSuchUser) {
            request.setAttribute(ERROR_MESSAGE, NOT_UNIQUE_LOGIN_FORM);
            request.getRequestDispatcher(PAGE_FOLDER_PATH + ApplicationConstants.PagesURLs.REGISTRATION_URL + PAGE_JSP_EXTENTION).forward(request, response);
        } else {
            String name = request.getParameter(NAME);
            String address = request.getParameter(ADDRESS);
            long userId = (long) UserList.usersList.size() + 1;
            //try save user. Get number of saved users
            int result = userDALService.save(new User(userId, name, address, login, password));


            if (result <= 0) {
                request.setAttribute(ERROR_MESSAGE, SMTH_WRONG_MESSAGE);
                request.getRequestDispatcher(PAGE_FOLDER_PATH + REGISTRATION_URL + PAGE_JSP_EXTENTION).forward(request, response);
            } else {
                response.sendRedirect(ApplicationConstants.PagesURLs.SHOW_ITEMS_URL);
            }
        }
    }
}
