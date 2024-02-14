package com.example.tarot;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class}, version = 7)
public abstract class CardsDatabase extends RoomDatabase {

    private static volatile CardsDatabase db;

    static CardsDatabase getDatabase(final Context context) {
        if (db == null) {
            synchronized (CardsDatabase.class) {
                if (db == null) {
                    db = Room.databaseBuilder(context.getApplicationContext(), CardsDatabase.class, "cards.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return db;
    }

    public abstract CardDao cardDao();

}