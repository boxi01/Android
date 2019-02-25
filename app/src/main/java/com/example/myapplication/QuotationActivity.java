package com.example.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class QuotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username",getString(R.string.username));
        String getQuotePhrase=getString(R.string.getQuote,username);
        TextView getQuote = findViewById(R.id.getQuote);
        getQuote.setText(getQuotePhrase);

    }

    private void refreshQuote(){
        TextView sampleQ = findViewById(R.id.getQuote);
        sampleQ.setText(R.string.refreshQuote);
        TextView sampleAuthor = findViewById(R.id.sampleAuthor);
        sampleAuthor.setText("~ "+getString(R.string.author));
        sampleAuthor.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refmenu,menu);
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
