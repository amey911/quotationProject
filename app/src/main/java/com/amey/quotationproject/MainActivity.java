package com.amey.quotationproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button exitbtn;
    Button openbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        openbtn = (Button) findViewById(R.id.open_button);
        exitbtn = (Button) findViewById(R.id.exit_button);

        openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opnIntent = new Intent(MainActivity.this, openActivity.class);

                startActivity(opnIntent);

            }
        });




        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });



    }





    public void onCreateDoc(View view) {

        Intent intent = new Intent(this, NewOptionsActivity.class);

        startActivity(intent);














    }
}