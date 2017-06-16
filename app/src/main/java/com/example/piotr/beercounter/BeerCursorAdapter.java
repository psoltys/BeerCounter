package com.example.piotr.beercounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.piotr.beercounter.BeerBase.TABLE_BEERS;

/**
 * Created by Piotr on 2017-06-15.
 */

public class BeerCursorAdapter  {

    private SQLiteDatabase database;
    private BeerBase dbHelper;
    private String[] allColumns = { BeerBase.COLUMN_ID,
            BeerBase.DATE, BeerBase.PRICE };
    public BeerCursorAdapter(Context context){
        dbHelper = new BeerBase(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Beer addBeers(Beer beers){
        ContentValues values = new ContentValues();
        values.put(BeerBase.PRICE, beers.getFinalPrice());
        values.put(BeerBase.DATE, beers.getDate());
        long insertID = database.insert(TABLE_BEERS, null, values);
        Cursor cursor = database.query(TABLE_BEERS,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        Beer newBeer = cursorToBeer(cursor);
        cursor.close();
        return newBeer;
    }
    public void deleteBeer(String date){
        database.execSQL("DELETE FROM " + TABLE_BEERS + " WHERE " + BeerBase.DATE + "=\"" + date + "\";");
    }

    public List<Beer> getAllBeers(){
        List<Beer> Beers = new ArrayList<Beer>();
        Cursor cursor = database.query(TABLE_BEERS,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Beer beer = cursorToBeer(cursor);
            Beers.add(beer);
            cursor.moveToNext();
        }
        cursor.close();
        return Beers;
    }

    private Beer cursorToBeer(Cursor cursor){
        Beer beer = new Beer();
        beer.setId(cursor.getLong(0));
        beer.setPrice(Double.parseDouble(cursor.getString(1)));
        return beer;
    }

    }