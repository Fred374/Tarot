package com.example.tarot;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Card.class}, version = 2)
public abstract class CardsDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
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