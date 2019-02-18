package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getQuote();
            }
        });

        //Favourite
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                favouriteQuotes();
            }
        });

        //Settings
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Settings();
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAbout();
            }
        });
    }

    public void openAbout(){
        Intent intent = new Intent(this, openAbout.class);
        startActivity(intent);
    }

    public void getQuote(){
        Intent intent = new Intent(this, getQuote.class);
        startActivity(intent);
    }

    public void favouriteQuotes(){
        Intent intent = new Intent(this, favouriteQuotes.class);
        startActivity(intent);
    }

    public void Settings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
