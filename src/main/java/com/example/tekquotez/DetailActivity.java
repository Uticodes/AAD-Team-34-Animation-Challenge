package com.example.tekquotez;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class DetailActivity extends AppCompatActivity {
    ImageButton Next;
    ImageButton Prev;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Next = findViewById(R.id.imageBtNext);
        Prev = findViewById(R.id.imageBtPrev);
        display = findViewById(R.id.text);


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.this.startActivity(new Intent(DetailActivity.this, NextDetailActivity.class));
                //Intent i = new Intent(DetailActivity.this, NextDetailActivity.class);
                //startActivity(i);
               // Animatoo.animateZoom(DetailActivity.this);
            }
        });


        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.this.startActivity(new Intent(DetailActivity.this, NextDetailActivity.class));
                //Intent i = new Intent(DetailActivity.this, NextDetailActivity.class);
                //startActivity(i);
                //Animatoo.animateSplit(DetailActivity.this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

