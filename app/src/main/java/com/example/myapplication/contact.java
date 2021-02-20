package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.net.URI;

public class contact extends AppCompatActivity {
    ImageButton fbbtn,mailbtn,zalobtn,gitbtn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //ánh xạ
        fbbtn = (ImageButton)findViewById(R.id.fbbtn);
        mailbtn = (ImageButton)findViewById(R.id.mailbtn);
        zalobtn = (ImageButton)findViewById(R.id.zalobtn);
        gitbtn = (ImageButton)findViewById(R.id.gitbtn);
        //set Listener
        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://www.facebook.com/vu.long.7169/");
            }
        });
        gitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL("https://github.com/comewithme1200");
            }
        });
        mailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        zalobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}