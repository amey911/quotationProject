package com.amey.quotationproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;




public class AddDialog extends AppCompatDialogFragment {

    private EditText edit_itemName;
    private EditText edit_itemQty;
    private EditText edit_itemRate;

    private AddDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        builder.setView(view).setTitle("Add Item").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String itemname = edit_itemName.getText().toString();
                String itmQty = edit_itemQty.getText().toString();
                String itemRate = edit_itemRate.getText().toString();

                listener.applyText(itemname, itmQty ,itemRate);
            }
        });

        edit_itemName = view.findViewById(R.id.et_item_name);
        edit_itemQty = view.findViewById(R.id.et_qty);
        edit_itemRate = view.findViewById(R.id.et_rate);


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must Implement AddDialogListener");
        }

    }

    public interface AddDialogListener {
        void applyText(String itemName, String itmQty, String itmRate);
    }

}
