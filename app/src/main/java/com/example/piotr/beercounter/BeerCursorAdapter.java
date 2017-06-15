package com.example.piotr.beercounter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Piotr on 2017-06-15.
 */

public class BeerCursorAdapter extends ResourceCursorAdapter {

    public BeerCursorAdapter(Context context, int layout, Cursor cursor, int flags){
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView baza = (TextView) view.findViewById(R.id.database);
        baza.setText(cursor.getString(cursor.getColumnIndex("name")));

    }
}