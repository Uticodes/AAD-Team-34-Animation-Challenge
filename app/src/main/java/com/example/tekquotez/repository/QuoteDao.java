package com.example.tekquotez.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tekquotez.model.Quote;

import java.util.List;

@Dao
public interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuotes(List<Quote> quotes);

    @Query("SELECT * FROM quotes")
    List<Quote> getAllQuotes();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateQuote(Quote quote);


    @Query("SELECT * FROM quotes WHERE is_favourite = 1")
    List<Quote> getFavouriteQuote();



}
