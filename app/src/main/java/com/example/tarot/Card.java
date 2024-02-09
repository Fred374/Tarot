package com.example.tarot;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cards")
public class Card {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "drawable")
    private int draw;

    @ColumnInfo(name = "drawable_large")
    private int draw_large;

    public Card(int id, String name, int draw, int draw_large) {
        this.id = id;
        this.name = name;
        this.draw = draw;
        this.draw_large = draw_large;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDraw() {
        return draw;
    }

    public int getDraw_large() {
        return draw_large;
    }
}
