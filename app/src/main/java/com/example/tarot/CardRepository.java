package com.example.tarot;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CardRepository {

    CardsDatabase db;
    CardDao dao;

    public CardRepository(Application app) {
        db = CardsDatabase.getDatabase(app);
        dao = db.cardDao();
    }

    public LiveData<List<Card>> getCard(int... id) {
        return dao.getCard(id);
    }

    public LiveData<List<Card>> getAll() {
        return dao.getAll();
    }

    public void insertAll(Card... cards) {
        dao.insertAll(cards);
    }

    public void insertAll(List<Card> cards) {
        dao.insertAll(cards);
    }

    public void deleteAll() {
        dao.deleteAll();
    }
}
