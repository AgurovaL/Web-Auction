package servlets;

import models.Item;
import service.ItemDALService;
import service.impl.ItemDALServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static models.ApplicationConstants.ErrorsStrings.ERROR_MESSAGE;
import static models.ApplicationConstants.ErrorsStrings.ITEMS_NOT_FOUND;
import static models.ApplicationConstants.FormsAttributes.ITEMS_LIST;
import static models.ApplicationConstants.FormsAttributes.KEYWORD;
import static models.ApplicationConstants.PagesAttributes.PAGE_FOLDER_PATH;
import static models.ApplicationConstants.PagesAttributes.PAGE_JSP_EXTENTION;
import static models.ApplicationConstants.PagesURLs.SHOW_ITEMS_URL;
import static models.ApplicationConstants.UserAttributes.LOGIN_AS_USERNAME;

public class SearchItemServlet extends HttpServlet {
    private ItemDALService itemDALService = new ItemDALServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ServletUtils.getUserLogin(request.getSession());

        String keyword = request.getParameter(KEYWORD);

        //find items by keyword
        List<Item> itemsList = itemDALService.findByKeyword(keyword);

        //if no such items - show error
        if (itemsList.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, ITEMS_NOT_FOUND);
        }

        request.setAttribute(ITEMS_LIST, itemsList);
        request.setAttribute(LOGIN_AS_USERNAME, login);   //put login to jsp
        request.getRequestDispatcher(PAGE_FOLDER_PATH + SHOW_ITEMS_URL + PAGE_JSP_EXTENTION).forward(request, response);

    }
}
