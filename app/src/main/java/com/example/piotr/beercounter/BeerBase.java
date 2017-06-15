package com.example.piotr.beercounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by piotr on 15.06.17.
 */

public final class BeerBase extends SQLiteOpenHelper implements Serializable{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "beer.db";
    public static final String TABLE_BEERS = "beers";
    public static final String COLUMN_ID = "_id";
    public static final String DATE = "date";
    public static final String PRICE = "price";

    public BeerBase(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_BEERS + "(" +
                COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATE + " TEXT, " +
                PRICE + " TEXT " + ");";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEERS);
        onCreate(db);
    }

    public void addBeers(String date, String price){
        ContentValues values = new ContentValues();
        values.put(PRICE, price);
        values.put(DATE, date);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BEERS, null, values);
        db.close();
    }

    public void deleteBeer(String date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BEERS + " WHERE " + DATE + "=\"" + date + "\";");
    }
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BEERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        BeerCursorAdapter adapter = new BeerCursorAdapter(this, R.layout.activity_archive, c, 0);
        this.setListAdapter(adapter);

       // c.moveToFirst();

       /* while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("date"))!=null);
            {
                dbString+= c.getString(c.getColumnIndex("date"));
                dbString+= c.getString(c.getColumnIndex("price"));
                dbString+= "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    */
    }


}
