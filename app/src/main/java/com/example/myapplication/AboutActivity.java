package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView aboutUs = findViewById(R.id.aboutUs);
        String text = getString(R.string.aboutUs);
        text = text.replace("\n", "<br>");
        aboutUs.setText(Html.fromHtml(text));
        ImageView asiaPhoto = (ImageView) findViewById(R.id.asiaPhoto);
        ImageView pawelPhoto = (ImageView) findViewById(R.id.pawelPhoto);

        int imageResource = getResources().getIdentifier("@drawable/photo", null, this.getPackageName());
        asiaPhoto.setImageResource(imageResource);
        int imageResource2 = getResources().getIdentifier("@drawable/img", null, this.getPackageName());
        pawelPhoto.setImageResource(imageResource2);
    }
}
