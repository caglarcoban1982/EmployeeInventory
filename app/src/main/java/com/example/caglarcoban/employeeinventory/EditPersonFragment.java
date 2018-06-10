package com.example.caglarcoban.employeeinventory;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditPersonFragment extends Fragment {

    private EditText nameText;
    private EditText surnameText;
    private EditText ageText;
    private EditText phoneText;


    public EditPersonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_person, container, false);

        nameText = view.findViewById(R.id.edit_name);
        surnameText = view.findViewById(R.id.edit_surname);
        ageText = view.findViewById(R.id.edit_age);
        phoneText = view.findViewById(R.id.edit_phone);




        return view;


    }


}
