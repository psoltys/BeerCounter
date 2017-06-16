package com.example.piotr.beercounter;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by piotr on 08.06.17.
 */

public class ActivityBeer extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button saveButton;
    private Button BeerPrice;
    private Button Archive;
    private EditText cena;
    private TextView cenaOstateczna;
    private TextView liczbaPiw;
    private int liczPiw;
    private BeerCursorAdapter beerDB;
    private Beer beerList;
    Calendar c;
    String date;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        Archive = (Button) findViewById(R.id.Show_Archive);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        saveButton = (Button) findViewById(R.id.saveButton);
        BeerPrice = (Button) findViewById(R.id.BeerPrice);

        // cena  = (EditText) findViewById(R.id.editText2);
        cenaOstateczna = (TextView) findViewById(R.id.textView2);
        liczbaPiw = (TextView) findViewById(R.id.liczbaPiw);

        c = Calendar.getInstance();

        liczPiw = 0;
        liczbaPiw.setText(Double.toString(liczPiw));
        //cena.setText('0');


        beerList = new Beer();
        beerList.setPrice(0);


        button.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                if (liczPiw == 0)
                    liczPiw = 0;
                else
                    liczPiw--;
                liczbaPiw.setText(Double.toString(liczPiw));

                beerList.setQuantity(liczPiw);
                cenaOstateczna.setText("Cena Ostateczna " + beerList.getFinalPrice());
            }
        }));
        button2.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                liczPiw++;
                liczbaPiw.setText(Double.toString(liczPiw));
                beerList.setQuantity(liczPiw);
                cenaOstateczna.setText("Cena Ostateczna " + beerList.getFinalPrice());
            }
        }));
        saveButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                int year = c.get(Calendar.YEAR); // get the current year
                int month = c.get(Calendar.MONTH); // month...
                int day = c.get(Calendar.DAY_OF_MONTH); // current day in the month
                date = ( year + "/" + month + "/" + day);
                beerList.setDate(date);
                Intent intent = new Intent(ActivityBeer.this, ActivityArchive.class);
                beerDB = new BeerCursorAdapter(this);
                beerDB.open();

                List<Beer> values = beerDB.getAllBeers();

                ArrayAdapter<Beer> adapter = new ArrayAdapter<Beer>(this,android.R.layout.simple_list_item_1,values);

                adapter.add(beers);
                adapter.notifyDataSetChanged();
                intent.putExtra("base", beerList);
                startActivity(intent);

                //liczPiw = 0;
                //liczbaPiw.setText(Double.toString(liczPiw));

            }
        }));
        Archive.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBeer.this, ActivityArchive.class);
                startActivity(intent);
            }
        }));
        BeerPrice.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                showBeerDialog();
            }
        }));
    }


        private void showBeerDialog()
    {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Ustaw Cenę Piwa");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER |  InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                beerList.setPrice( Double.parseDouble(input.getText().toString()));
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        builder.show();
    }



}

