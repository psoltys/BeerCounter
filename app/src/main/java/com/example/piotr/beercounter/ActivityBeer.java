package com.example.piotr.beercounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by piotr on 08.06.17.
 */

public class ActivityBeer extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button saveButton;
    private Button Archive;
    private EditText cena;
    private TextView cenaOstateczna;
    private TextView liczbaPiw;
    private double liczPiw;
    public static BeerBase BeerDB;
    Calendar c;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        Archive = (Button)findViewById(R.id.Show_Archive);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        saveButton = (Button) findViewById(R.id.saveButton);
        cena  = (EditText) findViewById(R.id.editText2);
        cenaOstateczna = (TextView) findViewById(R.id.textView2);
        liczbaPiw = (TextView) findViewById(R.id.liczbaPiw);
        c = Calendar.getInstance();
        liczPiw = 0;
        liczbaPiw.setText( Double.toString(liczPiw) );
        //cena.setText('0');

        BeerDB = new BeerBase(this, null, null,1);

        button.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                if(liczPiw == 0)
                    liczPiw=0;
                else
                    liczPiw--;
                liczbaPiw.setText( Double.toString(liczPiw) );
                cenaOstateczna.setText("Cena Ostateczna: "+ Double.toString(liczPiw*Double.parseDouble(cena.getText().toString())));
            }
        }));
        button2.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                liczPiw++;
                liczbaPiw.setText( Double.toString(liczPiw) );
                cenaOstateczna.setText("Cena Ostateczna: "+ Double.toString(liczPiw*Double.parseDouble(cena.getText().toString())));
            }
        }));
        saveButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                int year = c.get(Calendar.YEAR); // get the current year
                int month = c.get(Calendar.MONTH); // month...
                int day = c.get(Calendar.DAY_OF_MONTH); // current day in the month
                date = ("Year / month / day: "+ year + "/" + month + "/" + day);
                BeerDB.addBeers(date,cenaOstateczna.getText().toString());
                liczPiw = 0;
                liczbaPiw.setText( Double.toString(liczPiw) );
                cenaOstateczna.setText(BeerDB.databaseToString());
            }
        }));
        Archive.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBeer.this, ActivityArchive.class);
                //intent.putExtra("base", BeerDB);
                startActivity(intent);
            }
        }));
}
}