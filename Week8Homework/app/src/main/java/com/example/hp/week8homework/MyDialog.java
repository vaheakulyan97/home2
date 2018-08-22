package com.example.hp.week8homework;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MyDialog extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_add_people, container,false);
        addItem(view);
        return view;
    }

    private void addItem(final View view) {
        final EditText name = view.findViewById(R.id.id_for_name);
        final EditText surname = view.findViewById(R.id.id_for_surname);
        Button button = view.findViewById(R.id.add_item);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).addPeople(name.getText().toString(), surname.getText().toString());
                dismiss();
            }
        });
    }
}
