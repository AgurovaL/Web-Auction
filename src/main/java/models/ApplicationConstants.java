package models;

public interface ApplicationConstants {
    interface Roles {
        String GUEST = "GUEST";
        String USER = "USER";
        String GUEST_LOGIN = "guest";
    }

    interface PagesAttributes {
        String PAGE_FOLDER_PATH = "/pages";
        String PAGE_JSP_EXTENTION = ".jsp";
    }

    interface PagesURLs {
        String LOGIN_URL = "/login";
        String REGISTRATION_URL = "/registration";

        String SHOW_ITEMS_URL = "/showItems";
        String SHOW_MY_ITEM_URL = "/showMyItem";
        String CREATE_ITEM_URL = "/createItem";
    }

    interface ErrorsStrings {
        String ERROR_MESSAGE = "errorMessage";
        String ITEM_NOT_FOUND = "No such item";
        String USER_NOT_FOUND = "No such user";
        String ITEMS_NOT_FOUND = "No such items";
        String USERS_NOT_FOUND = "No such users";
        String INVALID_LOGIN_FORM = "Invalid login or password";
        String NOT_UNIQUE_LOGIN_FORM = "Not unique login/password";
        String BID_LOWER_OFFER = "Your bid can't be less then the best offer!";
        String BID_LOWER_INCREMENT = "Your bid can't change less then the bid increment!";
        String BIDDING_FINISHED = "too late";
        String SMTH_WRONG_MESSAGE = "Sorry, something went wrong((";
        String WRONG_DATE = "Parsing date error";
    }

    interface UserAttributes {
        String LOGIN_AS_USERNAME = "login";
        String USER_ID = "userId";
        String LOGIN = "login";
        String PASSWORD = "password";
        String NAME = "name";
        String ADDRESS = "address";
        String ITEMS_FOR_SALE = "itemsForSale";
        String ITEMS_IN_BASKET = "itemsInBasket";
    }

    interface ItemAttributes {
        String ITEM_ID = "itemId";
        String TITLE = "title";
        String START_PRICE = "startPrice";
        String BID_INC = "bidInc";
        String DESCRIPTION = "description";
        String STOP_DATE = "stopDate";
        String DATE_FORMAT = "dd-MMM-yy";
        String DATE_STANDART_FORMAT = "yyyy-MM-dd";
        String BEST_OFFER = "bestOffer";
        String FIRST_BIDDER = "firstBidder";
        String SELLER = "seller";
        String BIDDERS = "bidders";

    }

    interface FormsAttributes {
        String BID = "bid";
        String KEYWORD = "keyword";
        String ITEMS_LIST = "itemsList";
        String USERS_LIST = "usersList";
    }

    interface MapAttributes {
       String ITEM =  "item";
       String BID =  "bid";
       String ID = "id";
    }
}
