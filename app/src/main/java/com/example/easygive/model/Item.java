package com.example.easygive.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey
    @NonNull
    public String id;
    public String item_type;
    public String condition;
    public String location;
    public String contact;
    public String imageUrl;
    public boolean isFavorite;

    public Item(String id, String item_type, String condition, String location, String contact,
                String imageUrl, boolean isFavorite) {
        this.id = id;
        this.item_type = item_type;
        this.condition = condition;
        this.location = location;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }
}
