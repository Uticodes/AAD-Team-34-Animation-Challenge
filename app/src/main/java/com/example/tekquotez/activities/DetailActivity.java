package com.example.tekquotez.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.tekquotez.R;
import com.example.tekquotez.model.Quote;
import com.example.tekquotez.repository.RoomDb;
import com.example.tekquotez.services.ApiService;
import com.example.tekquotez.services.ServiceBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class DetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "TEKQUOTEZ";
    ImageButton Next;
    ImageButton Prev;
    TextView display;
    TextView quoteAuthor;
    CardView quoteCard;
    private Call<List<Quote>> quoteCall;
    private ApiService apiService;
    private List<Quote> quotes;
    private int quoteIndex = 0;
    private ImageView favouriteImage;

    private Quote currentQuote;

    private RoomDb roomDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Next = findViewById(R.id.imageBtNext);
        Prev = findViewById(R.id.imageBtPrev);
        display = findViewById(R.id.text);
        quoteCard = findViewById(R.id.quoteCard);
        quoteAuthor = findViewById(R.id.author);
        favouriteImage = findViewById(R.id.imageFavourite);

        roomDb = RoomDb.getDatabase(this);

        makeApiCall();

        Next.setOnClickListener(view -> {
            populateViews();
            Animatoo.animateZoom(DetailActivity.this);
        });


        Prev.setOnClickListener(view -> {
            prevQuote();
           Animatoo.animateSplit(DetailActivity.this);
        });

        favouriteImage.setOnClickListener(view -> {

            saveQuote();
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void makeApiCall(){
        apiService = ServiceBuilder.buildService(ApiService.class);
        quoteCall = apiService.getQuotes();
        quoteCall.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> quotes = response.body();
                roomDb.quoteDao().insertQuotes(quotes);
                populateViews();
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "No Internet Connection. Please connect to the Internet", Toast.LENGTH_LONG).show();
                populateViews();
            }
        });
    }

    private void populateViews() {
        List<Quote> allQuotes = roomDb.quoteDao().getAllQuotes();
        if (allQuotes.size() > 0) {
            currentQuote = allQuotes.get(quoteIndex);
            String quoteText = allQuotes.get(quoteIndex).getQuote();
            String author = allQuotes.get(quoteIndex).getAuthor();
            display.setText(quoteText);
            quoteAuthor.setText(author);
            int isFavourite = allQuotes.get(quoteIndex).getIsFavourite();
            changeImage(isFavourite);
            //if (quoteIndex < quotes.size() - 1)
            if (quoteIndex < allQuotes.size() - 1)
                quoteIndex++;
            else {
                Toast.makeText(this, "This is the last quote", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void prevQuote() {
        List<Quote> allQuotes = roomDb.quoteDao().getAllQuotes();
        if (allQuotes.size() > 0) {
            if (quoteIndex >= 2) {
                quoteIndex = quoteIndex - 2;
                currentQuote = allQuotes.get(quoteIndex);
                String quoteText = allQuotes.get(quoteIndex).getQuote();
                String author = allQuotes.get(quoteIndex).getAuthor();
                display.setText(quoteText);
                quoteAuthor.setText(author);
                int isFavourite = allQuotes.get(quoteIndex).getIsFavourite();
                changeImage(isFavourite);
            } else {
                Toast.makeText(this, "This is the first quote", Toast.LENGTH_SHORT).show();
            }
            //if (quoteIndex < quotes.size() - 1)
            if (quoteIndex < allQuotes.size() - 1)
                quoteIndex++;
            else {
                Toast.makeText(this, "This is the last quote", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh){
            makeApiCall();
        }

        if (id == R.id.star_fav){
            saveQuote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveQuote() {

        if (currentQuote.getIsFavourite() == 0) {
            favouriteImage.setBackground(getResources().getDrawable(R.drawable.ic_heart_filled));
            currentQuote.setIsFavourite(1);
            roomDb.quoteDao().updateQuote(currentQuote);
            Toast.makeText(this,"Quote Saved Successfully",Toast.LENGTH_SHORT).show();
        }else {
            favouriteImage.setBackground(getResources().getDrawable(R.drawable.ic_heart_unfilled));
            currentQuote.setIsFavourite(0);
            roomDb.quoteDao().updateQuote(currentQuote);
            Toast.makeText(this,"Quote removed Successfully",Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(this,"Quote id = " + currentQuote.getId(),Toast.LENGTH_SHORT).show();

//        Toast.makeText(this,"Quote Saved Successfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.favourite_quotes){
            Intent intent = new Intent(this,FavouriteQuoteActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void changeImage(int isfavourite){
        if (isfavourite == 0){
            favouriteImage.setBackground(getResources().getDrawable(R.drawable.ic_heart_unfilled));

        }else {
            favouriteImage.setBackground(getResources().getDrawable(R.drawable.ic_heart_filled));

        }
    }
}

