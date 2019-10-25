package com.example.tekquotez.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tekquotez.model.Quote;

@Database(entities = {Quote.class}, version = 1)
public abstract class RoomDb extends RoomDatabase {

    static final String DATABASE_NAME = "quote_db";

    private static RoomDb INSTANCE;

    public abstract QuoteDao quoteDao();

    public static RoomDb getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                    , RoomDb.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
