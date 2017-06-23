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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BeerFragment extends Fragment {

    private Button button;
    private Button button2;
    private Button saveButton;
    private Button BeerPrice;
    private Button addBeers;
    private Button Archive;
    private TextView cenaOstateczna;
    private TextView liczbaPiw;
    private TextView beerPrioe;
    private int liczPiw;
    private Beer beerList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    int realBeerNumber;
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
        addBeers = (Button) view.findViewById(R.id.addBeers);
        cenaOstateczna = (TextView) view.findViewById(R.id.textView2);
        liczbaPiw = (TextView) view.findViewById(R.id.liczbaPiw);
        beerPrioe = (TextView) view.findViewById(R.id.beerPrice);

        c = Calendar.getInstance();

        liczPiw = 0;
        realBeerNumber = 0;
        liczbaPiw.setText(Integer.toString(liczPiw));

        beerList = new Beer();
        beerList.setPrice(0);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("BeerList");
        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                if (liczPiw == 0)
                    liczPiw = 0;
                else
                    liczPiw--;
                beerList.setQuantity(liczPiw);
                liczbaPiw.setText(Integer.toString(liczPiw));

            }
        }));

        button2.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                liczPiw++;
                beerList.setQuantity(liczPiw);
                liczbaPiw.setText(Integer.toString(liczPiw));

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

        addBeers.setOnClickListener((new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                realBeerNumber += beerList.getQuantity();

                beerList.setQuantity(Integer.parseInt(liczbaPiw.getText().toString()));
                beerList.setFinalPrice((beerList.getFinalPrice() + (beerList.getQuantity() * beerList.getPrice())));
                beerList.setQuantity(0);
                beerList.setPrice(0.0);
                liczPiw = 0;
                liczbaPiw.setText(Integer.toString(liczPiw));
                beerList.setQuantity(realBeerNumber);
                cenaOstateczna.setText("Cena Ostateczna " + beerList.getFinalPrice());
                beerPrioe.setText("Cena piwa:");

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
                beerPrioe.setText("Cena piwa: " + beerList.getPrice());
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

               // mDatabase.child("Date").setValue(date);
                mDatabase.push().setValue(beerList);

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