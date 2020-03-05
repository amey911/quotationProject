package com.amey.quotationproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewOptionsActivity extends AppCompatActivity {

    private DBManager dbManager;

    public static final String MY_PREFS_NAME = "clientList";

    Button btnOk;

    Bundle b;

    pojo bean;
    ArrayList<pojo> clientlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.new_options);









        final EditText name = findViewById(R.id.project_name_id);
        final EditText location = findViewById(R.id.project_location_id);
        final EditText contact = findViewById(R.id.project_contact_id);
        final EditText subject = findViewById(R.id.project_subject_id);
        final EditText room = findViewById(R.id.project_room_id);
        final EditText email = findViewById(R.id.client_email);
        final EditText notes = findViewById(R.id.notes);


        bean = new pojo();


        dbManager = new DBManager(this);
        dbManager.open();


        Button btnOk = findViewById(R.id.project_ok_btn);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




//                final String dbname = name.getText().toString();
//                final String dblocation = location.getText().toString();
//                final String dbcontact = contact.getText().toString();
//                final String dbsubject = subject.getText().toString();
//                final String dbroom = room.getText().toString();
//                final String dbemail = email.getText().toString();
//
//                dbManager.insert(dbname, dblocation,dbcontact,dbsubject,dbroom,dbemail);
//
//                Log.e("db", "client info added to database ");
//
//



                //SharedPreferences.Editor editor = getSharedPreferences("clientList",MODE_PRIVATE);
                SharedPreferences sharedPreferences = getSharedPreferences("clientList", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("clientName", name.getText().toString());
                editor.putString("clientLoc", location.getText().toString());
                editor.putString("clientSub", subject.getText().toString());
                editor.putString("clientContact", contact.getText().toString());
                editor.putString("clientRoom", room.getText().toString());
                editor.putString("clientEmail", email.getText().toString());
                editor.putString("clientNotes", notes.getText().toString());

                editor.apply();


                Log.e("#Secure putData Data", name.getText().toString());




                Intent New = new Intent(getApplicationContext(), NewWindow.class);

                startActivity(New);




            }
        });


    }
}


