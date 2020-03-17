package com.amey.quotationproject;

import android.R.layout;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class EditDialog extends AppCompatDialogFragment {

    private AutoCompleteTextView edit_itemName;
    private EditText edit_itemQty;
    private EditText edit_itemRate;


    private EditDialogListener editDialogListener;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_item_dialog_layout, null);

        builder.setView(view).setTitle("Edit Item").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String itemname = edit_itemName.getText().toString();
                String itmQty = edit_itemQty.getText().toString();
                String itemRate = edit_itemRate.getText().toString();



                editDialogListener.applyEditItemText(itemname, itmQty ,itemRate);
            }
        });



        String[] items = getResources().getStringArray(R.array.item_contents_array);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getActivity(), layout.simple_list_item_1, items);



        edit_itemName =  view.findViewById(R.id.et_item_name);
        edit_itemQty = view.findViewById(R.id.et_qty);
        edit_itemRate = view.findViewById(R.id.et_rate);

        edit_itemName.setAdapter(itemAdapter);



        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            editDialogListener = (EditDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must Implement editDialogListener");
        }

    }

    public interface EditDialogListener {

        void applyEditItemText(String itemName, String itmQty, String itmRate);
    }

}
