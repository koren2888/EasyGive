package com.example.easygive.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.easygive.EasyGiveApplication;

@Database(entities = {Item.class}, version = 55)
abstract class AppLocalDbRepository extends RoomDatabase {
    public abstract ItemDAO itemDAO();
}

public class AppLocalDb{
    static public AppLocalDbRepository getAppDb() {
        return Room.databaseBuilder(EasyGiveApplication.getMyContext(),
                        AppLocalDbRepository.class,
                        "dbFileName.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    private AppLocalDb(){}
}

