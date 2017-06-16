package com.example.piotr.beercounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.piotr.beercounter.Fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private Button new_act;
    private Button Archive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getFragmentManager()
               .beginTransaction()
               .replace(R.id.content_frame, MainFragment.newInstance("cos"),"mainFrame")
               .commit();
    }
}
