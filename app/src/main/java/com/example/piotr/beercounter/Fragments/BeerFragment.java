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
    private TextView cenaOstateczna;
    private TextView liczbaPiw;
    private int liczPiw;
    private Beer beerList;
    Calendar c;
    String date;
    AlertDialog.Builder builder;



    public static BeerFragment newInstance(String loading_msg) {
        BeerFragment beerFragment = new BeerFragment();
        if (loading_msg != null) {
            Bundle args = new Bundle();
            args.putString("msg", loading_msg);
            beerFragment.setArguments(args);
        }
        return beerFragment;
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

        cenaOstateczna = (TextView) view.findViewById(R.id.textView2);
        liczbaPiw = (TextView) view.findViewById(R.id.liczbaPiw);

        c = Calendar.getInstance();

        liczPiw = 0;
        liczbaPiw.setText(Integer.toString(liczPiw));

        beerList = new Beer();
        beerList.setPrice(0);


        button.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                if (liczPiw == 0)
                    liczPiw = 0;
                else
                    liczPiw--;
                liczbaPiw.setText(Integer.toString(liczPiw));

                beerList.setQuantity(liczPiw);
                cenaOstateczna.setText("Cena Ostateczna " + beerList.getFinalPrice());
            }
        }));

        button2.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                liczPiw++;
                liczbaPiw.setText(Integer.toString(liczPiw));
                beerList.setQuantity(liczPiw);
                cenaOstateczna.setText("Cena Ostateczna " + beerList.getFinalPrice());
            }
        }));
        Archive.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, ArchiveFragment.newInstance(null),"archiveFragment")
                        .addToBackStack("archive")
                        .commit();
            }
        }));
        BeerPrice.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                showBeerDialog();
            }
        }));
        saveButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                showSaveDialog();}
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

    private void showSaveDialog()
    {
        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Zapisać Aktywność?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = c.get(Calendar.YEAR); // get the current year
                int month = c.get(Calendar.MONTH); // month...
                int day = c.get(Calendar.DAY_OF_MONTH); // current day in the month
                date = ( year + "/" + month + "/" + day);
                beerList.setDate(date);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, ArchiveFragment.newInstance(beerList),"beerFragment")
                        .addToBackStack("archive")
                        .commit();
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