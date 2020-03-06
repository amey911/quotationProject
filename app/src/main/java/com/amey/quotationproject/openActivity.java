package com.amey.quotationproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.amey.quotationproject.DatabaseHelper.TABLE_NAME;

public class openActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private DBManager dbManager;

    RecyclerView openRecView;
    openRecyclerAdapter openAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<openData> opnData = new ArrayList<>();
    String name = "", loc = "", contact ="", subject = "" , room = "", email = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_window);

        openRecView = (RecyclerView) findViewById(R.id.open_rec);
        openRecView.setHasFixedSize(true);

        openRecView.setAdapter(openAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        openRecView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(openRecView.getContext(),
                layoutManager.getOrientation());
        openRecView.addItemDecoration(dividerItemDecoration);

        openAdapter = new openRecyclerAdapter(this, opnData);

        openRecView.setAdapter(openAdapter);

//        mRecyclerView.setAdapter(mRecyclerAdapter);


        dbManager = new DBManager(this);
        dbManager.open();

//        viewAll();

        Cursor cursor = dbManager.getAllData();

        if (cursor.moveToFirst()) {
            do {
                opnData.add(new openData(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        Log.e("data", "data added to array open" +opnData);










           openAdapter.notifyDataSetChanged();


    }












        }








