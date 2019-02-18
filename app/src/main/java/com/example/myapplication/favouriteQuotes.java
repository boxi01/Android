package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class favouriteQuotes extends AppCompatActivity {

    private Button authorButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_quotes);

        authorButton = (Button) findViewById(R.id.authorButton);
        authorButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                refreshQuote();
            }
        });
    }

    public void refreshQuote(){
        Intent intent = new Intent(this, authorInfo.class);
        startActivity(intent);
    }
}
