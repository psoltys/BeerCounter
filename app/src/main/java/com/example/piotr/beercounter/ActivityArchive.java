package com.example.piotr.beercounter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Piotr on 2017-06-15.
 */

public class ActivityArchive extends ListActivity {

    private BeerCursorAdapter beerDB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        Intent i = getIntent();
        Beer beers = (Beer)i.getSerializableExtra("base");

        beerDB = new BeerCursorAdapter(this);
        beerDB.open();

        List<Beer> values = beerDB.getAllBeers();

        ArrayAdapter<Beer> adapter = new ArrayAdapter<Beer>(this,android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);

        adapter.add(beers);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        beerDB.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        beerDB.close();
        super.onPause();
    }



}
