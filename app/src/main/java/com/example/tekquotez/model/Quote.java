package com.example.tekquotez.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "quotes")
public class Quote {

    @Expose
    private String quote, author;

    @ColumnInfo(name = "is_favourite")
    private int isFavourite = 0;

    @PrimaryKey(autoGenerate = true)
    @Expose
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }
}

