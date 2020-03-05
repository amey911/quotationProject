package com.amey.quotationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.amey.quotationproject.DatabaseHelper.TABLE_NAME;

public class openActivity extends AppCompatActivity {


    RecyclerView openRecView;
    RecyclerView.Adapter openAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<openData> openData = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_window);

        openRecView = (RecyclerView) findViewById(R.id.open_rec);
        openRecView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        openRecView.setLayoutManager(layoutManager);

        DatabaseHelper openhelper = new DatabaseHelper(this);

//        SQLiteDatabase opendatabase = DatabaseHelper
////




    }

}