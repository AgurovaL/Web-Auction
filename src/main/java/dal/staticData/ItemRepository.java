package dal.staticData;

import models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> getAll();

    Item findByID(Long id);

    List<Item> findByKeyword(String keyword);

    int delete(Long id);

    int deleteAll();

    int save(Item item);
}
