package servlets;

import dal.jsonFiles.JSONWriter;
import models.Item;
import models.User;
import service.ItemDALService;
import service.UserDALService;
import service.impl.ItemDALServiceImpl;
import service.impl.UserDALServiceImpl;
import staticData.ItemList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static models.ApplicationConstants.ErrorsStrings.*;
import static models.ApplicationConstants.ItemAttributes.*;
import static models.ApplicationConstants.PagesAttributes.PAGE_FOLDER_PATH;
import static models.ApplicationConstants.PagesAttributes.PAGE_JSP_EXTENTION;
import static models.ApplicationConstants.PagesURLs.CREATE_ITEM_URL;
import static models.ApplicationConstants.PagesURLs.SHOW_ITEMS_URL;
import static models.ApplicationConstants.UserAttributes.LOGIN_AS_USERNAME;

public class CreateItemServlet extends HttpServlet {
    private UserDALService userDALService = new UserDALServiceImpl();
    private ItemDALService itemDALService = new ItemDALServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ServletUtils.getUserLogin(request.getSession());
        request.setAttribute(LOGIN_AS_USERNAME, login);   //put login to jsp
        request.getRequestDispatcher(PAGE_FOLDER_PATH + CREATE_ITEM_URL + PAGE_JSP_EXTENTION).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ServletUtils.getUserLogin(request.getSession());
        //get parameners
        String title = request.getParameter(TITLE);
        String description = request.getParameter(DESCRIPTION);
        String dateString = request.getParameter(STOP_DATE);
        float startPrice = Float.parseFloat(request.getParameter(START_PRICE));
        int bidIncrement = Integer.parseInt(request.getParameter(BID_INC));
        long itemId = (long) ItemList.itemsList.size() + 1;

        //get and parse date
        Date date = new Date();
        try {
            date = new SimpleDateFormat(DATE_STANDART_FORMAT).parse(dateString);
        } catch (ParseException e) {
            request.setAttribute(ERROR_MESSAGE, WRONG_DATE);
            request.getRequestDispatcher(PAGE_FOLDER_PATH + CREATE_ITEM_URL + PAGE_JSP_EXTENTION).forward(request, response);
        }

        //create new item
        Item item = new Item(itemId, title, description, startPrice, bidIncrement, date);

        //try find user as seller of the item
        Optional<User> userOptional = userDALService.findByLogin(login);
        if (!userOptional.isPresent()) {
            request.setAttribute(ERROR_MESSAGE, USER_NOT_FOUND);
            item.setSeller(0L);
        } else {
            item.setSeller(userOptional.get().getUserId());
            item.calcBestOffer();
            userOptional.get().addItemForSale(item);
        }

        //try save item. Get number of saved items
        int result = itemDALService.save(item);

        //from writer
        new JSONWriter().writeUsers();

        if (result <= 0) {
            request.setAttribute(ERROR_MESSAGE, SMTH_WRONG_MESSAGE);
            request.getRequestDispatcher(PAGE_FOLDER_PATH + CREATE_ITEM_URL + PAGE_JSP_EXTENTION).forward(request, response);
        } else {
            response.sendRedirect(SHOW_ITEMS_URL);
        }
    }
}
