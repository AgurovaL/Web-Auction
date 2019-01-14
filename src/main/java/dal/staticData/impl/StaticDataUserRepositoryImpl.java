package dal.staticData.impl;

import dal.jsonFiles.JSONWriter;
import dal.staticData.UserRepository;
import models.User;
import staticData.UserList;

import java.util.List;

public class StaticDataUserRepositoryImpl implements UserRepository {
    public List<User> getAll() {
        return UserList.usersList;
    }

    public User findByID(Long id) {
        return UserList.usersList.stream().filter(user -> user.getUserId() == id)
                .findFirst().orElse(null);
    }

    public User findByLogin(String login) {
        for (User user : UserList.usersList) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return new User();
    }

    //returns the number of saved elems
    public int save(User user) {
        int prevSize = UserList.usersList.size();

        if (user != null) {
            UserList.usersList.add(user);
            //from writer
            new JSONWriter().addUserToJSON(user);
        }
        return UserList.usersList.size() - prevSize;
    }

    //returns the number of deleted elems
    public int delete(Long id) {
        int prevSize = UserList.usersList.size();

        for (User user : UserList.usersList) {
            if (user.getUserId() == id) {
                UserList.usersList.remove(user);
            }
        }
        return prevSize - UserList.usersList.size();
    }
}
