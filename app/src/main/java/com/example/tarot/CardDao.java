package com.example.tarot;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {

    @Insert
    void insertAll(List<Card> cards);

    @Query("DELETE FROM cards")
    void deleteAll();

    @Query("SELECT * FROM cards")
    LiveData<List<Card>> getAll();

    @Query("SELECT * FROM cards WHERE id IN (:id)")
    LiveData<List<Card>> getCard(int... id);

}
