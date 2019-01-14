package dal.jsonFiles;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Item;
import models.User;
import staticData.ItemList;
import staticData.UserList;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static models.ApplicationConstants.ErrorsStrings.USERS_NOT_FOUND;
import static models.ApplicationConstants.ItemAttributes.*;
import static models.ApplicationConstants.UserAttributes.*;

public class JSONReader {
    private final String FILE_NAME = "usersInfo.json";

    public void readUsers() {
        List<User> usersList = new ArrayList<>();

        try {
            //найти файл с информацией
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(FILE_NAME).getFile());

            //считать и замапить
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
            ObjectMapper mapper = new ObjectMapper();
            List<JsonNode> jsonNodes = Arrays.asList((mapper.readValue(reader, JsonNode[].class)));
            reader.close();

            //получить список пользователей
            UserList.usersList = createUsersListFromJsonNodeList(jsonNodes);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    //получить список пользователей из файла
    private List<User> createUsersListFromJsonNodeList(List<JsonNode> jsonNodes) {
        List<User> usersList = new ArrayList<>();
        if (jsonNodes != null) {
            for (JsonNode jsonNode : jsonNodes) {
                User user = createUserFromJsonNode(jsonNode);
                //добавить готового пользователя
                usersList.add(user);
            }
        } else {
            System.out.println(USERS_NOT_FOUND);
        }
        return usersList;
    }

    private User createUserFromJsonNode(JsonNode jsonNode) {
        //замапить все поля пользователя
        User user = new User();
        user.setUserId(Long.parseLong(jsonNode.get(USER_ID).asText()));
        user.setName(jsonNode.get(NAME).textValue());
        user.setAddress(jsonNode.get(ADDRESS).textValue());
        user.setLogin(jsonNode.get(LOGIN).textValue());
        user.setPassword(jsonNode.get(PASSWORD).textValue());

        //получить товары на продажу
        JsonNode itemsForSale = jsonNode.get(ITEMS_FOR_SALE);
        putItemsForSale(itemsForSale, user);

        //получить товары на покупку
        JsonNode itemsInBasket = jsonNode.get(ITEMS_IN_BASKET);
        putItemsToBasket(itemsInBasket, user);
        return user;
    }

    //получить товары на продажу
    private void putItemsForSale(JsonNode itemsForSale, User user) {
        if (itemsForSale.size() > 0) {
            for (int i = 0; i < itemsForSale.size(); i++) {
                Item item = createItemFromJsonNode(itemsForSale.get(i));
                user.addItemForSale(item);
                ItemList.itemsList.add(item);
            }
        }
    }

    //получить товары на покупку
    private void putItemsToBasket(JsonNode itemsInBasket, User user) {
        if (itemsInBasket.size() > 0) {
            for (int i = 0; i < itemsInBasket.size(); i++) {
                Item item = createItemFromJsonNode(itemsInBasket.get(i).get("item"));
                float bid = Float.parseFloat(itemsInBasket.get(i).get("bid").asText());
                user.addItemToBasket(item, bid);
            }
        }
    }

    //получить товар
    private Item createItemFromJsonNode(JsonNode jsonNode) {
        //замапить все поля товара
        Item item = new Item();
        item.setItemId(Long.parseLong(jsonNode.get(ITEM_ID).asText()));
        item.setTitle(jsonNode.get(TITLE).textValue());
        item.setDescription(jsonNode.get(DESCRIPTION).textValue());
        item.setStartPrice(Float.parseFloat(jsonNode.get(START_PRICE).asText()));
        item.setBidIncrement(Integer.parseInt(jsonNode.get(BID_INC).asText()));

        //обработать дату
        String dateString = jsonNode.get(STOP_DATE).textValue();
        Date date = new Date();
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        item.setStopDate(date);

        item.setBestOffer(Float.parseFloat(jsonNode.get(BEST_OFFER).asText()));
        item.setFirstBidder(Long.parseLong(jsonNode.get(FIRST_BIDDER).asText()));
        item.setSeller(Long.parseLong(jsonNode.get(SELLER).asText()));

        //получить словарь пользователей и ставок на товар
        JsonNode bidders = jsonNode.get(BIDDERS);
        putBidders(bidders, item);

        //вернуть готовый товар
        return item;
    }

    //    //получить словарь пользователей и ставок на товар
    private void putBidders(JsonNode bidders, Item item) {
        if (bidders.size() > 0) {
            for (int i = 0; i < bidders.size(); i++) {
                Long id = Long.parseLong(bidders.get(i).get("id").asText());
                float bid = Float.parseFloat(bidders.get(i).get("bid").asText());
                item.addBidder(id, bid);
            }
        }
    }
}