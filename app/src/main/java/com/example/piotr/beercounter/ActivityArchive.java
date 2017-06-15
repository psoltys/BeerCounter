package com.example.piotr.beercounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Piotr on 2017-06-15.
 */

public class ActivityArchive extends AppCompatActivity {
    TextView baza;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        Intent i = getIntent();
        baza = (TextView) findViewById(R.id.database);
        BeerBase BeersDB = ActivityBeer.BeerDB;
       // BeerBase BeerDB = (BeerBase)i.getSerializableExtra("base");
        baza.setText(BeersDB.databaseToString());

    }
}
