package com.amey.quotationproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class OpnDisplayDialog extends AppCompatDialogFragment {

    private TextView op_client_name;
    private TextView op_client_loc;
    private TextView op_client_num;
    private TextView op_client_sub;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {




        AlertDialog.Builder opnBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.opn_dialog_layout, null);

//        edit_itemName = view.findViewById(R.id.et_item_name);

        op_client_name = view.findViewById(R.id.opn_dialog_client_name);
        op_client_loc = view.findViewById(R.id.opn_dialog_client_loc);
        op_client_num = view.findViewById(R.id.opn_dialog_client_contact);
        op_client_sub = view.findViewById(R.id.opn_dialog_client_subject);




        return opnBuilder.create();
    }
}
