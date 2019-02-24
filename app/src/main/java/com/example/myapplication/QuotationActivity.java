package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        TextView sampleQ = findViewById(R.id.getQuote);
        sampleQ.setText(R.string.refreshQuote);
        TextView sampleAuthor = findViewById(R.id.sampleAuthor);
        sampleAuthor.setText("~ "+getString(R.string.author));
        sampleAuthor.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getNewQ:
                refreshQuote();
                return true;
            case R.id.addToFav:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
