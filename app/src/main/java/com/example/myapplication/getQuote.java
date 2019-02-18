package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class getQuote extends AppCompatActivity {

    private ImageButton refreshButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quote);

        refreshButton = (ImageButton) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                refreshQuote();
            }
        });
    }

    public void refreshQuote(){
        Intent intent = new Intent(this, refreshQuote.class);
        startActivity(intent);
    }
}
