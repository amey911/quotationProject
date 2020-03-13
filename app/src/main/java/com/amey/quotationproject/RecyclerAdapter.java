package com.amey.quotationproject;

import android.annotation.SuppressLint;
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

    private OnClickItem mOnClickItem;

    int mLastposition = 0;


    //private RemoveClickListner;



    public RecyclerAdapter(ArrayList<RecyclerData> myList, OnClickItem onClickItem){
        this.myList = myList;
        this.mOnClickItem = onClickItem;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_row, parent, false);

        ViewHolder holder = new ViewHolder(view, mOnClickItem );
        return  holder;



    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView etTitleTextView;
        private TextView etDescView;
        private TextView etAmountView;

        OnClickItem onClickItem;



        public ViewHolder(@NonNull View itemView, OnClickItem onClickItem) {
            super(itemView);


            etTitleTextView = itemView.findViewById(R.id.txtTitle);

            etDescView = itemView.findViewById(R.id.txtDesc);

            etAmountView = itemView.findViewById(R.id.txtAmount);

            this.onClickItem = onClickItem;


            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onClickItem.onClickItem(getAdapterPosition());
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("onBindViewHolder: ", myList.size() + "");
            int t = position+1;

        if(myList !=null) {

                holder.etTitleTextView.setText(t  + ". " + myList.get(position).getTitle());


            holder.etDescView.setText(myList.get(position).getDescription());
            holder.etAmountView.setText(myList.get(position).getAmount());



        }








    }


    @Override
    public int getItemCount() {
        return(null != myList?myList.size():0);
    }



public interface OnClickItem{
        void onClickItem(int pos);

}









}

