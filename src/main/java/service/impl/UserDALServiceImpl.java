package service.impl;

import dal.staticData.UserRepository;
import dal.staticData.impl.StaticDataUserRepositoryImpl;
import models.User;
import service.UserDALService;

import java.util.List;
import java.util.Optional;

public class UserDALServiceImpl implements UserDALService {
    private UserRepository userRepository = new StaticDataUserRepositoryImpl();

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> findByID(Long id) {
        return Optional.of(userRepository.findByID(id));
    }

    public Optional<User> findByLogin(String login) {
        return Optional.of(userRepository.findByLogin(login));
    }

    public int save(User user) {
        return userRepository.save(user);
    }

    public int delete(Long id) {
        return userRepository.delete(id);
    }
}
