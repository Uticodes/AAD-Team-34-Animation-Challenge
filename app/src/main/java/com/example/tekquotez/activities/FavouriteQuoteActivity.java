package com.example.tekquotez.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tekquotez.R;
import com.example.tekquotez.adapter.QuoteRecyclerAdapter;
import com.example.tekquotez.model.Quote;
import com.example.tekquotez.repository.RoomDb;

import java.util.List;

public class FavouriteQuoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_quote);

        setTitle("Favourite Quotes");

        RoomDb mRoomDb = RoomDb.getDatabase(this);
        List<Quote> favouriteQuote = mRoomDb.quoteDao().getFavouriteQuote();
        RecyclerView recyclerView = findViewById(R.id.quote_list);
        QuoteRecyclerAdapter adapter = new QuoteRecyclerAdapter(favouriteQuote,this,mRoomDb);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
