package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import myadapter.MyAdapter;
import quotation.Quotation;

public class FavouriteActivity extends AppCompatActivity {

    private Button authorButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        ListAdapter adapter = new MyAdapter(this, R.id.quotationListRow, getMockQuotations());

        ListView listView = findViewById(R.id.favouriteList);
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            //TODO finish implementation of onItemClick method - obtaining author info from adapter
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent!=null && parent.getClass().getName().equals("MyAdapter")) {
                    String author = "";
                    if (author != null && !author.equals("")) {
                        String authorEncoded = URLEncoder.encode(author);
                        infoAboutAuthor(authorEncoded);
                    }
                    else {
                        String textForToast = getString(R.string.unknownAuthorInfo);
                        Toast.makeText(getApplicationContext(), textForToast, Toast.LENGTH_LONG).show();
                    }

                }
                else if (parent != null) {
                    Toast.makeText(FavouriteActivity.this, "debugujemy: "+parent.getClass().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        listView.setOnItemClickListener(listener);

        AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage(getString(R.string.deleteConfirmation));
                /*TODO implement listener for Positive button
                    builder.setPositiveButton(getString(R.string.yes), new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        deleteFavourite();
                    }
                });*/
                builder.setNegativeButton(getString(R.string.no), null);
                builder.create().show();
                return false;
            }
        };

        listView.setOnItemLongClickListener(longClickListener);
        /*authorButton = (Button) findViewById(R.id.authorButton);
        authorButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                infoAboutAuthor();
            }
        });*/
    }

    //TODO implement method deleting a favourite quotation
    private void deleteFavourite(){

    }

    public void infoAboutAuthor(String authorEncoded){

        String url = "https://en.wikipedia.org/wiki/Special:Search?search=" + authorEncoded;
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        if(webIntent.resolveActivity(getPackageManager())!=null){
            startActivity(webIntent);
        }
    }

    public ArrayList<Quotation> getMockQuotations() {
        ArrayList<Quotation> quotationList = new ArrayList<>();
        //TODO dodac 10 quoation do listy
        //Quotation newQuotation = new Quotation();
        // quotationList.add(newQuotation);
        return quotationList;
    }

    //TODO if no elements on list don't show menu, if there are some, show menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage(getString(R.string.deleteAllConfirmation));

                /*TODO implement listener for Positive button
                    builder.setPositiveButton(getString(R.string.yes), new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        deleteAllFavs();
                        hideRemoveAllButton();
                    }
                });*/
                builder.setNegativeButton(getString(R.string.no), null);
                builder.create().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO implement method deleting all favourite quotations
    private void deleteAllFavs(){

    }

    private void hideRemoveAllButton() {
        Button raButton = findViewById(R.id.deleteAll);
        raButton.setVisibility(View.INVISIBLE);
    };

}
