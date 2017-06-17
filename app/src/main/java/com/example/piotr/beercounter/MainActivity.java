package com.example.piotr.beercounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.piotr.beercounter.Fragments.ArchiveFragment;
import com.example.piotr.beercounter.Fragments.MainFragment;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button new_act;
    private Button Archive;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getFragmentManager()
               .beginTransaction()
               .replace(R.id.content_frame, MainFragment.newInstance("cos"),"mainFrame")
               .commit();
    }

    public void showDialog(final ArchiveFragment archiveFragment, final List<Beer> listBeer, final int actualIndex)
    {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Zapisać Aktywność?");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER |  InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                archiveFragment.deleteBeer(listBeer.get(actualIndex).getId());
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
