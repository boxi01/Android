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

import java.util.ArrayList;
import java.util.List;

import databases.Database;
import databases.DatabaseExRoom;
import myadapter.MyAdapter;
import quotation.QHolder;
import quotation.Quotation;
import tasks.MyHTTPAsyncTask;

public class QuotationActivity extends AppCompatActivity {


    // Adapter object linking the data source and the ListView
    MyAdapter adapter = null;

    // Hold references to View objects
    private TextView sampleQ;
    private TextView sampleAuthor;
    private String methodName;

    private boolean isRefVis;
    private boolean isAddVis;
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
        List<Quotation> quotationList = new ArrayList<>();
        final QHolder myQHolder = new QHolder(quotationList);

        if (methodName.equals("Room")) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    myQHolder.setQuotations(DatabaseExRoom.getInstance(QuotationActivity.this).databaseDao().getQuotations());
                }
            }).start();
            Log.d("DEBUG", "Prawdzimwy room q");


        } else {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    myQHolder.setQuotations(DatabaseExRoom.getInstance(QuotationActivity.this).databaseDao().getQuotations());
                }
            }).start();
            Log.d("DEBUG", "Prawdzimwy sql q");
        }

        quotationList = myQHolder.getQuotations();
        // Create the adapter linking the data source to the ListView
        adapter = new MyAdapter(this, R.layout.quotation_list_row, quotationList);
        isAddVis=false;
        isRefVis=true;
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
                isAddVis=true;
                invalidateOptionsMenu();
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
                            DatabaseExRoom.getInstance(QuotationActivity.this).databaseDao().addQuotation(quotation);
                        }
                    }).start();
                    Log.d("DEBUG", "Prawdzimwy room");

                } else {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Database.getInstance(QuotationActivity.this).addQuotation(quotation);
                        }
                    }).start();
                    Log.d("DEBUG", "Prawdzimwy sql");
                }
                Log.d("DEBUG", "TESTUJEMY 6"+quoteAuthor+" i "+quoteText);

                adapter.add(quotation);
                isAddVis=false;
                invalidateOptionsMenu();

                return true;
             }
             return super.onOptionsItemSelected(item);
        }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem addToFav = menu.findItem(R.id.addToFav);
        MenuItem refresh = menu.findItem(R.id.getNewQ);
        addToFav.setVisible(isAddVis);
        refresh.setVisible(isRefVis);
        return super.onPrepareOptionsMenu(menu);
    }

    public void showProgressBar(){
            invalidateOptionsMenu();
            ProgressBar progressBar = findViewById(R.id.progressBar);

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



