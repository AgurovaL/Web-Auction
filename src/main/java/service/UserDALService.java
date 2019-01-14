package service;

import models.User;

import java.util.List;
import java.util.Optional;

public interface UserDALService {
    List<User> getAll();

    Optional<User> findByID(Long id);

    Optional<User> findByLogin(String login);

    int save(User user);

    int delete(Long id);
}
