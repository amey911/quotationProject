package com.amey.quotationproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<RecyclerData> myList;

    int mLastposition = 0;


    //private RemoveClickListner;



    public RecyclerAdapter(ArrayList<RecyclerData> myList){
        this.myList = myList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_row, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return  holder;
    }





    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView etTitleTextView;
        private TextView etDescView;
        private TextView etAmountView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            etTitleTextView = itemView.findViewById(R.id.txtTitle);

            etDescView = itemView.findViewById(R.id.txtDesc);

            etAmountView = itemView.findViewById(R.id.txtAmount);


        }




    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("onBindViewHolder: ", myList.size() + "");

        if(myList !=null) {
            holder.etTitleTextView.setText(myList.get(position).getTitle());
            holder.etDescView.setText(myList.get(position).getDescription());
            holder.etAmountView.setText(myList.get(position).getAmount());
        }






    }


    @Override
    public int getItemCount() {
        return(null != myList?myList.size():0);
    }













}

