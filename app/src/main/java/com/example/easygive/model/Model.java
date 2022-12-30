package com.example.easygive.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();
    private static List<Item> itemsList;


    public static Model instance() {
        return _instance;
    }

    private Model(){
        itemsList = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            addItem(new Item("type " + i,"Good", "Here", "" + i,
                    "", false));
        }
    }

    public List<Item> getAllItems() {
        return itemsList;
    }

    public void addItem(Item st) {
        itemsList.add(st);
    }
}
