package com.example.tarot;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cards")
public class Card {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int id;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "drawable")
    private final int draw;

    @ColumnInfo(name = "description")
    private final String description;

    public Card(int id, String name, int draw, String description) {
        this.id = id;
        this.name = name;
        this.draw = draw;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
