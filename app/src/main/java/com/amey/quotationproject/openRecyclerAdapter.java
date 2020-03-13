package com.amey.quotationproject;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class openRecyclerAdapter extends RecyclerView.Adapter<openRecyclerAdapter.ViewHolder> {

//   ArrayList<openData> openData = new ArrayList<>();

    private ArrayList<openData> opnData;

    private Context context;

    private onOpnClick mOnOpnClick;




   //    private ArrayList<openData> openData;

//    private ArrayList opn_name, opn_loc;


public openRecyclerAdapter(ArrayList<openData> opnData, onOpnClick onOpnClick){
    this.opnData= opnData;
    this.mOnOpnClick = onOpnClick;

}
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView openName;
        private TextView openLoc;
        onOpnClick onOpnClick;


        public  ViewHolder(@NonNull View itemView, onOpnClick onOpnClick)  {
            super(itemView);
            openName = itemView.findViewById(R.id.open_proj_name);
            openLoc = itemView.findViewById(R.id.open_proj_loc);

            this.onOpnClick = onOpnClick;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onOpnClick.onOpnClick(getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public openRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View openView = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_window_row, parent, false);

        ViewHolder openHolder = new ViewHolder(openView, mOnOpnClick);

        return openHolder;
    }







    @Override
    public void onBindViewHolder(@NonNull openRecyclerAdapter.ViewHolder holder, int position) {

        Log.d("onBindViewHolder: ", opnData.size() + "");
//        holder.itemView.setTag(openData.get(position));   for click fucntion

            openData opndata = opnData.get(position);

            holder.openName.setText(opnData.get(position).getDocName());
            holder.openLoc.setText(opnData.get(position).getDocLoc());


    }

    @Override
    public int getItemCount() {
//        return(null != openList?openList.size():0);
        return opnData.size();
    }




public interface onOpnClick{
    void onOpnClick(int position);
}

}
