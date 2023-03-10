package com.example.easygive.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Model {
    private static final Model _instance = new Model();
    private static List<Item> itemsList;
    private static Map<String, Item> favoriteItems;


    public static Model instance() {
        return _instance;
    }

    private Model(){
        itemsList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if(i==2)
                addItem(new Item(String.valueOf(i), "Gardening","Good", "Here", "" + i,
                        "", false));
            else
                addItem(new Item(String.valueOf(i), "type " + i,"Good", "Here", "" + i,
                        "", false));
        }

        favoriteItems = new HashMap<>();
    }

    public List<Item> getAllItems() {
        return new ArrayList<Item>(itemsList);
    }

    public List<Item> getMyItems() {
        return itemsList;
    }

    public Item getItem(int pos) {
        return itemsList.get(pos);
    }

    public void updateFavorites(Item item) {
        if (!item.isFavorite) {
            favoriteItems.put(item.id, item);
        } else {
            favoriteItems.remove(item.id);
        }
        item.isFavorite = !item.isFavorite;
    }

    public List<Item> getFavorites() {
        return new LinkedList<>(favoriteItems.values());
    }

    public void addItem(Item st) {
        itemsList.add(st);
    }
}
