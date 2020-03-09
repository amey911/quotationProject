package com.amey.quotationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
    String name = "", loc = "", contact = "", subject = "", room = "", email = "";


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

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);



//        (new RecyclerTouchListener(getActivity(), recyclerview, new RecyclerTouchListener.ClickListener()






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
        Log.e("data", "data added to array open" + opnData);


        openAdapter.notifyDataSetChanged();

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(openRecView);

    }


















    openData deleteditem = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();

           switch (direction) {
               case ItemTouchHelper.LEFT:
                   if (opnData != null && pos >= 0 && pos < opnData.size())
                       dbManager.delete(opnData.get(pos).getDocName());

                   deleteditem = opnData.get(pos);
                   Log.e("del", "onSwiped: "+deleteditem );

                   opnData.remove(pos);
                   openAdapter.notifyItemRemoved(pos);
                   break;


               case ItemTouchHelper.RIGHT:

           }
        }

    };















}








