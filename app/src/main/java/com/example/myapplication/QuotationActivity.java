package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import databases.Database;
import databases.DatabaseExRoom;
import myadapter.MyAdapter;
import quotation.Quotation;
import tasks.MyHTTPAsyncTask;

public class QuotationActivity extends AppCompatActivity {


    // Adapter object linking the data source and the ListView
    MyAdapter adapter = null;

    // Hold references to View objects
    String quoteText = null;
    String quoteAuthor = null;
    TextView sampleQ;
    TextView sampleAuthor;
    String methodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPreferences.getString("username",getString(R.string.defaultUsername));
        String getQuotePhrase=getString(R.string.getQuote,username);
        TextView getQuote = findViewById(R.id.getQuote);
        getQuote.setText(getQuotePhrase);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        methodName = settings.getString("databaseMethod", "Room");

        List<Quotation> quotationList;

        if (methodName.equals("Room")) {

            quotationList = DatabaseExRoom.getInstance(this).databaseDao().getQuotations();

        } else {

            quotationList = Database.getInstance(this).getQuotations();
        }

        // Create the adapter linking the data source to the ListView
        adapter = new MyAdapter(this, R.layout.quotation_list_row, quotationList);

    }

    private void refreshQuote(){
        sampleQ = findViewById(R.id.getQuote);
        sampleQ.setText(R.string.refreshQuote);
        sampleAuthor = findViewById(R.id.sampleAuthor);
        sampleAuthor.setText("~ "+getString(R.string.author));
        sampleAuthor.setVisibility(View.VISIBLE);
      //  quoteText = sampleQ.toString();
      //  quoteAuthor = sampleAuthor.toString();
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
                MyHTTPAsyncTask refreshTask = new MyHTTPAsyncTask(this);
                refreshTask.execute();
                Log.d("DEBUG", "TESTUJEMY3 ");
                return true;
            case R.id.addToFav:
                Log.d("DEBUG", "TESTUJEMY 1");
                // Get all the information from the data fields
                final String quoteText = sampleQ.getText().toString();
                final String quoteAuthor = sampleAuthor.getText().toString();
                Log.d("DEBUG", "TESTUJEMY 4"+quoteAuthor+" i "+quoteText);

                // Create a new contact
                final Quotation quotation = new Quotation(quoteText, quoteAuthor);
                Log.d("DEBUG", "TESTUJEMY 5"+quotation.getQuoteText()+" i "+quotation.getQuoteAuthor());

                //Add to list
                if (methodName.equals("Room")) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DatabaseExRoom.getInstance(getApplicationContext()).databaseDao().addQuotation(quotation);
                        }
                    }).start();
                    Log.d("DEBUG", "Prawdzimwy room");

                } else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Database.getInstance(getApplicationContext()).addQuotation(quotation);
                        }
                    }).start();Log.d("DEBUG", "Prawdzimwy sql");
                }
                Log.d("DEBUG", "TESTUJEMY 6"+quoteAuthor+" i "+quoteText);

                adapter.add(quotation);


                return true;
             }
             return super.onOptionsItemSelected(item);
        }

        public void showProgressBar(){
            android.support.v7.view.menu.ActionMenuItemView addToFav = findViewById(R.id.addToFav);
            android.support.v7.view.menu.ActionMenuItemView refresh = findViewById(R.id.getNewQ);
            ProgressBar progressBar = findViewById(R.id.progressBar);
            addToFav.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.VISIBLE);;
            progressBar.setVisibility(View.VISIBLE);
        }

        public void hideProgressBar(Quotation quotation) {

            sampleQ = findViewById(R.id.getQuote);
            sampleAuthor = findViewById(R.id.sampleAuthor);
            sampleQ.setText(quotation.getQuoteText());
            sampleAuthor.setText(quotation.getQuoteAuthor());

            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



