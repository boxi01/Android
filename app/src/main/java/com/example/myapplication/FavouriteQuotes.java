package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FavouriteQuotes extends AppCompatActivity {

    private Button authorButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_quotes);

        authorButton = (Button) findViewById(R.id.authorButton);
        authorButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                infoAboutAuthor();
            }
        });
    }

    public void infoAboutAuthor(){
        String website = "https://pl.wikipedia.org/wiki/Mahatma_Gandhi";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(website));
        startActivity(intent);
    }
}
