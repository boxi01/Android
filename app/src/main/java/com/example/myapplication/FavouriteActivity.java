package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

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

        /*authorButton = (Button) findViewById(R.id.authorButton);
        authorButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                infoAboutAuthor();
            }
        });*/
    }

    public void infoAboutAuthor(){

        String url = "https://pl.wikipedia.org/wiki/Mahatma_Gandhi";
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
}
