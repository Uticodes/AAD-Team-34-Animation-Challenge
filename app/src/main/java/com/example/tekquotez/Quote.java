package com.example.tekquotez;

import com.google.gson.annotations.Expose;

public class Quote {
    @Expose
    private String quote, author;

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

}

