package service;

import models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDALService {
    List<Item> getAll();

    Optional<Item> findByID(Long id);

    List<Item> findByKeyword(String keyword);

    int delete(Long id);

    int deleteAll();

    int save(Item item);
}
