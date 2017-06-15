package com.example.piotr.beercounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button new_act;
    private Button Archive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new_act = (Button) findViewById(R.id.new_activity);
        Archive = (Button) findViewById(R.id.Show_Archive);
        new_act.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityBeer.class);
                startActivity(intent);
            }
        }));

        Archive.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityArchive.class);
                startActivity(intent);
            }
        }));

    }
}
