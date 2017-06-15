package com.example.piotr.beercounter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by piotr on 15.06.17.
 */

public final class BeerBase extends SQLiteOpenHelper{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    public BeerBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "beer.db";
    public static final String TABLE_BEERS = "beers";
    public static final String COLUMN_ID = "_id";
    public static final String DATE = "date";
    public static final String PRICE = "price";
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE" + TABLE_BEERS + "(" +
                COLUMN_ID +"INTEGER PRIMARY KEY AUTOINCREMENT," +
                DATE + "TEXT" +
                PRICE + "TEXT" + ");";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_BEERS);
        onCreate(db);
    }


}
