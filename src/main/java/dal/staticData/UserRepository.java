package dal.staticData;

import models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User findByID(Long id);

    User findByLogin(String login);

    int save(User user);

    int delete(Long id);
}
