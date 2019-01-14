package dal.staticData.impl;

import dal.staticData.ItemRepository;
import models.Item;
import staticData.ItemList;

import java.util.ArrayList;
import java.util.List;

public class StaticDataItemRepositoryImpl implements ItemRepository {
    public List<Item> getAll() {
        return ItemList.itemsList;
    }

    public Item findByID(Long id) {
        return ItemList.itemsList.stream().filter(item -> item.getItemId() == id)
                .findFirst().orElse(null);
    }

    public List<Item> findByKeyword(String keyword) {
        List<Item> keyItems = new ArrayList<>();
        for (Item item : ItemList.itemsList) {
            if (item.getTitle().equals(keyword) || item.getTitle().contains(keyword)) {
                keyItems.add(item);
            }
        }
        return keyItems;
    }

    //return the number of deleted elems
    public int delete(Long id) {
        int prevSize = ItemList.itemsList.size();

        for (Item item : ItemList.itemsList) {
            if (item.getItemId() == id) {
                ItemList.itemsList.remove(item);
            }
        }
        return prevSize - ItemList.itemsList.size();
    }

    //return the number of deleted elems
    public int deleteAll() {
        int prevSize = ItemList.itemsList.size();

        ItemList.itemsList.clear();
        return prevSize - ItemList.itemsList.size();
    }

    //return the number of saved elems
    public int save(Item item) {
        int prevSize = ItemList.itemsList.size();

        if (item != null) {
            ItemList.itemsList.add(item);
        }

        return ItemList.itemsList.size() - prevSize;
    }
}
