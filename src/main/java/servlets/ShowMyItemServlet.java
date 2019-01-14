package servlets;

import models.Item;
import models.User;
import service.UserDALService;
import service.impl.UserDALServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static models.ApplicationConstants.ErrorsStrings.ERROR_MESSAGE;
import static models.ApplicationConstants.ErrorsStrings.USER_NOT_FOUND;
import static models.ApplicationConstants.PagesAttributes.PAGE_FOLDER_PATH;
import static models.ApplicationConstants.PagesAttributes.PAGE_JSP_EXTENTION;
import static models.ApplicationConstants.PagesURLs.SHOW_ITEMS_URL;
import static models.ApplicationConstants.PagesURLs.SHOW_MY_ITEM_URL;
import static models.ApplicationConstants.UserAttributes.LOGIN_AS_USERNAME;

public class ShowMyItemServlet extends HttpServlet {
    private UserDALService userDALService = new UserDALServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ServletUtils.getUserLogin(request.getSession());
        request.setAttribute(LOGIN_AS_USERNAME, login);   //put login to jsp

        //найти юзера по логину
        Optional<User> userOptional = userDALService.findByLogin(login);

        if (!userOptional.isPresent()) {
            request.setAttribute(ERROR_MESSAGE, USER_NOT_FOUND);
            request.getRequestDispatcher(PAGE_FOLDER_PATH + SHOW_ITEMS_URL + PAGE_JSP_EXTENTION).forward(request, response);
        } else {
            User user = userOptional.get();
            Set<Item> itemsForSale = user.getItemsForSale(); //товары на продажу

            Map<Item, Float> itemsAndPricesInBasket = user.getItemsInBasket(); //товары на покупку
            Set<Item> itemsInBasket = itemsAndPricesInBasket.keySet();
            System.out.println("itemsInBasket" + itemsAndPricesInBasket);

            request.setAttribute("itemsForSale", itemsForSale);
            request.setAttribute("itemsInBasket", itemsInBasket);

            request.getRequestDispatcher(PAGE_FOLDER_PATH + SHOW_MY_ITEM_URL + PAGE_JSP_EXTENTION).forward(request, response);
        }
    }
}
