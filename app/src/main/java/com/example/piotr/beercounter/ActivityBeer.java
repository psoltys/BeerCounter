package com.example.piotr.beercounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by piotr on 08.06.17.
 */

public class ActivityBeer extends AppCompatActivity {

    private Button button;
    private Button button2 ;
    private EditText cena;
    private TextView cenaOstateczna;
    private TextView liczbaPiw;
    private double liczPiw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        cena  = (EditText) findViewById(R.id.editText2);
        cenaOstateczna = (TextView) findViewById(R.id.textView2);
        liczbaPiw = (TextView) findViewById(R.id.liczbaPiw);

        liczPiw = 0;
        liczbaPiw.setText( Double.toString(liczPiw) );
        //cena.setText('0');



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
}
}
