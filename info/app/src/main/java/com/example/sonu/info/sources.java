package com.example.sonu.info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class sources extends AppCompatActivity {
    ImageView toi , bbc , espn , thu , mtv , time ,bbcs , cnn ,google , netg , cnbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        toi = (ImageView)findViewById(R.id.toi);
        bbc = (ImageView)findViewById(R.id.bbc);
        espn = (ImageView)findViewById(R.id.espn)
        ;
        thu = (ImageView)findViewById(R.id.thu);
        mtv = (ImageView)findViewById(R.id.mtv);
        time = (ImageView)findViewById(R.id.time);
        bbcs = (ImageView)findViewById(R.id.bbcs);
        cnn = (ImageView)findViewById(R.id.cnn);
        google = (ImageView)findViewById(R.id.google);
        netg = (ImageView)findViewById(R.id.netg);
        cnbc = (ImageView)findViewById(R.id.cnbc);

        toi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "the-times-of-india");
                startActivity(intent);
            }
        });
        bbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "bbc-news");
                startActivity(intent);
            }
        });
        espn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "espn");
                startActivity(intent);
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "the-hindu");
                startActivity(intent);
            }
        });
        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "mtv-news");
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "time");
                startActivity(intent);
            }
        });
        bbcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "bbc-news");
                startActivity(intent);
            }
        });
        cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "cnn");
                startActivity(intent);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "google-news");
                startActivity(intent);
            }
        });
        netg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "national-geographic");
                startActivity(intent);
            }
        });
        cnbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , newActivity.class);
                intent.putExtra("source1", "cnbc");
                startActivity(intent);
            }
        });
    }
}
