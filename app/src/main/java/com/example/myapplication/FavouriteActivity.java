package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import databases.Database;
import databases.DatabaseExRoom;
import myadapter.MyAdapter;
import quotation.QHolder;
import quotation.Quotation;


public class FavouriteActivity extends AppCompatActivity {
    private MyAdapter adapter = null;
    private String methodName;

    boolean isDeleteAllVis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        List<Quotation> quotationList = new ArrayList<>();
        final QHolder myQHolder = new QHolder(quotationList);


        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        methodName = settings.getString("databaseMethod", "Room");

        if (methodName.equals("Room")) {

            quotationList = DatabaseExRoom.getInstance(this).databaseDao().getQuotations();

        } else {

            quotationList = Database.getInstance(this).getQuotations();
        }

        if(quotationList.isEmpty())
            isDeleteAllVis=false;
        else
            isDeleteAllVis=true;
        invalidateOptionsMenu();
        adapter = new MyAdapter(this, R.layout.quotation_list_row, quotationList);

        ListView listView = findViewById(R.id.favouriteList);
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                {
                    Quotation quotation = (Quotation) parent.getItemAtPosition(position);
                    String author = quotation.getQuoteAuthor();
                    if (author != null && !author.equals("")) {
                        String authorEncoded = URLEncoder.encode(author);
                        infoAboutAuthor(authorEncoded);
                    }
                    else {
                        String textForToast = getString(R.string.unknownAuthorInfo);
                        Toast.makeText(getApplicationContext(), textForToast, Toast.LENGTH_LONG).show();
                    }

                }

            }
        };
        Log.d("DEBUG", "TESTUJEMYx 7");

        listView.setOnItemClickListener(listener);

        AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FavouriteActivity.this);
                builder.setMessage(getString(R.string.deleteConfirmation));
                    builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Quotation quotation=myQHolder.getQuotations().get(position);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    deleteFavourite(quotation);                                }
                            }).start();
                        }
                    });


                builder.setNegativeButton(getString(R.string.no), null);
                builder.create().show();
                return true;
            }
        };
        Log.d("DEBUG", "TESTUJEMYx 8");

        listView.setOnItemLongClickListener(longClickListener);
        /*authorButton = (Button) findViewById(R.id.authorButton);
        authorButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                infoAboutAuthor();
            }
        });*/
    }

    private void deleteFavourite(Quotation quotation){
        final Quotation quotationToDel=quotation;
        if (methodName.equals("Room")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DatabaseExRoom.getInstance(getApplicationContext()).databaseDao().deleteQuotation(quotationToDel);
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Database.getInstance(getApplicationContext()).deleteQuotation(quotationToDel);
                }
            }).start();
        }

        List<Quotation> quotationList = new ArrayList<>();
        final QHolder myQHolder = new QHolder(quotationList);

        if (methodName.equals("Room")) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    myQHolder.setQuotations(DatabaseExRoom.getInstance(FavouriteActivity.this).databaseDao().getQuotations());
                }
            }).start();

        } else {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    myQHolder.setQuotations(Database.getInstance(FavouriteActivity.this).getQuotations());
                }
            }).start();}

        quotationList=myQHolder.getQuotations();

        if(quotationList.isEmpty())
            isDeleteAllVis=false;
        else
            isDeleteAllVis=true;
        invalidateOptionsMenu();
    }

    public void addToAdapter(List<Quotation> quotations){
        adapter.addAll(quotations);
        MenuItem deleteAll = findViewById(R.id.deleteAll);
        if(!adapter.isEmpty()){
            deleteAll.setVisible(true);
        }
        else {
            deleteAll.setVisible(false);
        }
    }

    public void infoAboutAuthor(String authorEncoded){

        String url = getString(R.string.searchWiki) + authorEncoded;
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        if(webIntent.resolveActivity(getPackageManager())!=null){
            startActivity(webIntent);
        }
    }

    /*public ArrayList<Quotation> getMockQuotations() {
        ArrayList<Quotation> quotationList = new ArrayList<>();
        //Quotation newQuotation = new Quotation();
        // quotationList.add(newQuotation);
        return quotationList;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("DEBUG", "TESTUJEMYx 9");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favmenu,menu);
        Log.d("DEBUG", "TESTUJEMYx 10");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem deleteAll = menu.findItem(R.id.deleteAll);
        deleteAll.setVisible(isDeleteAllVis);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(FavouriteActivity.this);
                builder.setMessage(getString(R.string.deleteAllConfirmation));

                    builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            deleteAllFavs();                                        }
                                    }).start();
                                    isDeleteAllVis=false;
                                    invalidateOptionsMenu();
                                }
                            });

                    builder.setNegativeButton(getString(R.string.no), null);
                builder.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteAllFavs(){
        if (methodName.equals("Room")) {
            DatabaseExRoom.getInstance(getApplicationContext()).databaseDao().deleteAllQuotations();
        } else {
            Database.getInstance(getApplicationContext()).deleteAll();
        }
    }


}
