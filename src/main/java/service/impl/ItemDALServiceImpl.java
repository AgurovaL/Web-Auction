package service.impl;

import dal.staticData.ItemRepository;
import dal.staticData.impl.StaticDataItemRepositoryImpl;
import models.Item;
import service.ItemDALService;

import java.util.List;
import java.util.Optional;

public class ItemDALServiceImpl implements ItemDALService {
    private ItemRepository itemRepository = new StaticDataItemRepositoryImpl();

    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    public Optional<Item> findByID(Long id) {
        return Optional.of(itemRepository.findByID(id));
    }

    public List<Item> findByKeyword(String keyword) {
        return itemRepository.findByKeyword(keyword);
    }

    public int delete(Long id) {
        return itemRepository.delete(id);
    }

    public int deleteAll() {
        return itemRepository.deleteAll();
    }

    public int save(Item item) {
        return itemRepository.save(item);
    }
}
