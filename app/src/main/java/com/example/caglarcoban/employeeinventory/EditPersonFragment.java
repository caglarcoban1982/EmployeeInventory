package com.example.caglarcoban.employeeinventory;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.caglarcoban.employeeinventory.provider.EmployeeInventoryProvider;

public class EditPersonFragment extends Fragment {

    private static final String EMPLOYEE_ID = "employee_id";
    private EditText nameText;
    private EditText surnameText;
    private EditText ageText;
    private EditText phoneText;
    private int employeeId;

    public EditPersonFragment() {
        // Required empty public constructor
    }

    public static EditPersonFragment newInstance(int id){
        Bundle args = new Bundle();
        args.putInt(EMPLOYEE_ID, id);

        EditPersonFragment editPersonFragment = new EditPersonFragment();
        editPersonFragment.setArguments(args);
        return editPersonFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employeeId = getArguments().getInt(EMPLOYEE_ID);

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


        EmployeeInventoryProvider employeeInventoryProvider = EmployeeInventoryProvider.getInstance(getActivity());

        Employee employee = getEmployeeById(employeeId);




        return view;


    }

    private Employee getEmployeeById(int employeeId) {
        EmployeeInventoryProvider employeeInventoryProvider = EmployeeInventoryProvider.getInstance(getActivity());

        Cursor cursor = employeeInventoryProvider.query(EmployeeInventoryProvider.EmployeeContracts.CONTENT_URI, null,
                EmployeeInventoryProvider.EmployeeContracts.ID + "= ? ",
                new String[]{Integer.toString(employeeId)}, null);




    }


}
