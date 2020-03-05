package com.amey.quotationproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class openRecyclerAdapter extends RecyclerView.Adapter<openRecyclerAdapter.ViewHolder> {

   ArrayList<openData> openData = new ArrayList<>();
   openRecyclerAdapter(ArrayList<openData> openData) {
       this.openData = openData;
   }

    private Context context;





   //    private ArrayList<openData> openData;

//    private ArrayList opn_name, opn_loc;


public openRecyclerAdapter(Context context, ArrayList<openData> list){
    openData= list;
}
    public static class ViewHolder extends RecyclerView.ViewHolder {


        private TextView openName;
        private TextView openLoc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            openName = itemView.findViewById(R.id.open_proj_name);
            openLoc = itemView.findViewById(R.id.open_proj_loc);


        }
    }


    @NonNull
    @Override
    public openRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View openView = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_window_row, parent, false);

        ViewHolder openHolder = new ViewHolder(openView);

        return new ViewHolder(openView);
    }







    @Override
    public void onBindViewHolder(@NonNull openRecyclerAdapter.ViewHolder holder, int position) {

        Log.d("onBindViewHolder: ", openData.size() + "");
//        holder.itemView.setTag(openData.get(position));   for click fucntion

            openData opndata = openData.get(position);

            holder.openName.setText(openData.get(position).getDocName());
            holder.openLoc.setText(openData.get(position).getDocLoc());


//        holder.openName.setText(String.valueOf(opn_name.get(position)));
//        holder.openName.setText(String.valueOf(opn_loc.get(position)));

        //        if (openList != null){
//
//            holder.openName.setText(openList.get(position).getDocName());
//            holder.openLoc.setText(openList.get(position).getDocLoc());
//
//        }
    }

    @Override
    public int getItemCount() {
//        return(null != openList?openList.size():0);
        return openData.size();
    }


}
