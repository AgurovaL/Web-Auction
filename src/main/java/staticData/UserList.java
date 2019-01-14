package staticData;

import dal.jsonFiles.JSONReader;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    public static List<User> usersList = new ArrayList<>();

    static {
        //from reader
        JSONReader jsonReader = new JSONReader();
        jsonReader.readUsers();
    }
}
