package com.example.piotr.beercounter.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.piotr.beercounter.Beer;
import com.example.piotr.beercounter.BeerCursorAdapter;
import com.example.piotr.beercounter.R;

import java.util.Calendar;

public class BeerFragment extends Fragment {

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



    public static MainFragment newInstance(String loading_msg) {
        MainFragment loadingFragment = new MainFragment();
        if (loading_msg != null) {
            Bundle args = new Bundle();
            args.putString("msg", loading_msg);
            loadingFragment.setArguments(args);
        }
        return loadingFragment;
    }


    public BeerFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.beer_fragment, container, false);
        final Activity activity = getActivity();



        Archive = (Button) view.findViewById(R.id.Show_Archive);

        button = (Button) view.findViewById(R.id.button);
        button2 = (Button) view.findViewById(R.id.button2);
        saveButton = (Button) view.findViewById(R.id.saveButton);
        BeerPrice = (Button) view.findViewById(R.id.BeerPrice);

        // cena  = (EditText) findViewById(R.id.editText2);
        cenaOstateczna = (TextView) view.findViewById(R.id.textView2);
        liczbaPiw = (TextView) view.findViewById(R.id.liczbaPiw);

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
         //       Intent intent = new Intent(BeerFragment.this, ActivityArchive.class);
        /*        beerDB = new BeerCursorAdapter(this);
                beerDB.open();

                List<Beer> values = beerDB.getAllBeers();

                ArrayAdapter<Beer> adapter = new ArrayAdapter<Beer>(this,android.R.layout.simple_list_item_1,values);

                adapter.add(beers);
                adapter.notifyDataSetChanged();
                intent.putExtra("base", beerList);
                startActivity(intent);
*/
                //liczPiw = 0;
                //liczbaPiw.setText(Double.toString(liczPiw));

            }
        }));
        Archive.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Fragment newFragment = new ArchiveFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        }));
        BeerPrice.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                showBeerDialog();
            }
        }));


        return view;
    }
    private void showBeerDialog()
    {
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ustaw Cenę Piwa");

        final EditText input = new EditText(getActivity());
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