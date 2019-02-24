package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class QuotationActivity extends AppCompatActivity {

    private ImageButton refreshButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        String username=getString(R.string.username);
        String getQuotePhrase=getString(R.string.getQuote,username);
        TextView getQuote = findViewById(R.id.getQuote);
        getQuote.setText(getQuotePhrase);

        refreshButton = (ImageButton) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                refreshQuote();
            }
        });
    }

    public void refreshQuote(){
        Intent intent = new Intent(this, RefreshQuote.class);
        startActivity(intent);
    }
}
