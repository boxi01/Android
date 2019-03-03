package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import databases.DatabaseExRoom;

public class DashboardActivity extends AppCompatActivity {

    public void onDashboardButtonClicked(View v){
        View button1;
        View button2;
        View button3;
        View button4;

        button1 = findViewById(R.id.button1);

        //Favourite
        button2 = findViewById(R.id.button2);

        //Settings
        button3 = findViewById(R.id.button3);

        button4 = findViewById(R.id.button4);

        if (v != null) {
            if (v.equals(button1)) {
                getQuote();
            } else {
                if (v.equals(button2))
                    favouriteQuotes();
                else {
                    if (v.equals(button3)) {
                        settings();
                    } else {
                        if (v.equals(button4)) {
                            openAbout();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        if (prefs.getBoolean("first_run", true))
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DatabaseExRoom.getInstance(DashboardActivity.this).databaseDao().getQuotations();
                }
            }).start();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_run", false);
            editor.apply();
        }

    }

    public void openAbout(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void getQuote(){
        Intent intent = new Intent(this, QuotationActivity.class);
        startActivity(intent);
    }

    public void favouriteQuotes(){
        Intent intent = new Intent(this, FavouriteActivity.class);
        startActivity(intent);
    }

    public void settings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
