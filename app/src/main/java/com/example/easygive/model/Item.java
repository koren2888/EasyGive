package com.example.easygive.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String item_type;
    public String condition;
    public String location;
    public String contact;
    public String imageUrl;
    public boolean isFavorite;

    public Item(String title, String item_type, String condition, String location, String contact,
                String imageUrl, boolean isFavorite) {
        this.title = title;
        this.item_type = item_type;
        this.condition = condition;
        this.location = location;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }
}
