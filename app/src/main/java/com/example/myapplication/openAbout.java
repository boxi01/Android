package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class openAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_about);

        ImageView myPhoto = (ImageView) findViewById(R.id.myPhoto);
        ImageView myPhoto2 = (ImageView) findViewById(R.id.myPhoto2);

        int imageResource = getResources().getIdentifier("@drawable/photo", null, this.getPackageName());
        myPhoto.setImageResource(imageResource);
        myPhoto2.setImageResource(imageResource);
    }
}
