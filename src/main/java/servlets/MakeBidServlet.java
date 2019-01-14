package servlets;

import dal.jsonFiles.JSONWriter;
import models.Item;
import models.User;
import service.ItemDALService;
import service.UserDALService;
import service.impl.ItemDALServiceImpl;
import service.impl.UserDALServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static models.ApplicationConstants.ErrorsStrings.*;
import static models.ApplicationConstants.FormsAttributes.BID;
import static models.ApplicationConstants.ItemAttributes.ITEM_ID;
import static models.ApplicationConstants.PagesAttributes.PAGE_FOLDER_PATH;
import static models.ApplicationConstants.PagesAttributes.PAGE_JSP_EXTENTION;
import static models.ApplicationConstants.PagesURLs.*;
import static models.ApplicationConstants.Roles.GUEST_LOGIN;

public class MakeBidServlet extends HttpServlet {
    private HttpServletRequest request;
    private ItemDALService itemDALService = new ItemDALServiceImpl();
    private UserDALService userDALService = new UserDALServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        String login = ServletUtils.getUserLogin(request.getSession());

        if (login.equals(GUEST_LOGIN)) {        //если пользователь не залогинился
            response.sendRedirect(LOGIN_URL);
        } else {

            if (tryMakeBidOnItem(login)) {            //если все хорошо, показать корзину пользователя
                response.sendRedirect(SHOW_MY_ITEM_URL);
            } else {                            //если есть любая ошибка
                request.getRequestDispatcher(PAGE_FOLDER_PATH + SHOW_ITEMS_URL + PAGE_JSP_EXTENTION).forward(request, response);
            }
        }
    }

    private boolean tryMakeBidOnItem(String login) {
        //получить ставку
        float bid = Float.parseFloat(request.getParameter(BID));
        //на какой товар ставка?
        Long itemID = Long.parseLong(request.getParameter(ITEM_ID));

        Optional<Item> optionalItem = itemDALService.findByID(itemID);

        if (!optionalItem.isPresent()) {
            request.setAttribute(ERROR_MESSAGE, ITEM_NOT_FOUND);
            return false;
        } else {
            Item item = optionalItem.get();
            return checkParams(item, bid, login);
        }
    }

    private boolean checkParams(Item item, float bid, String login) {

        //если ставка происходит после назначенной даты - ошибка
        Date date = item.getStopDate();

        if (new Date().after(date)) {
            request.setAttribute(ERROR_MESSAGE, BIDDING_FINISHED);
            return false;
        }
        //если ставка меньше чем текущая - ошибка на странице
        if (bid < item.calcBestOffer()) {
            request.setAttribute(ERROR_MESSAGE, BID_LOWER_OFFER);
            return false;
        }

        //если ставка отличается меньше чем на инкремент -  ошибка на странице
        if (bid - item.getBestOffer() < item.getBidIncrement()) {
            request.setAttribute(ERROR_MESSAGE, BID_LOWER_INCREMENT);
            return false;
        }

        return tryAddItemToUser(item, bid, login);
    }

    private boolean tryAddItemToUser(Item item, float bid, String login) {
        //какой пользователь сделал ставку
        Optional<User> userOptional = userDALService.findByLogin(login);

        if (!userOptional.isPresent()) {
            request.setAttribute(ERROR_MESSAGE, USER_NOT_FOUND);
            return false;
        } else {
            User user = userOptional.get();

            //добавить товар в корзину пользователю

            user.addItemToBasket(item, bid);
            item.addBidder(user.getUserId(), bid);

            //from writer
            new JSONWriter().writeUsers();

            return true;
        }
    }
}