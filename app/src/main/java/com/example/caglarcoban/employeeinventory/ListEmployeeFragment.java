package com.example.caglarcoban.employeeinventory;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.caglarcoban.employeeinventory.provider.EmployeeInventoryProvider;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListEmployeeFragment extends Fragment implements AdapterView.OnItemClickListener{


    public ListEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_employee, container, false);

        ListView listView = view.findViewById(R.id.employee_list);

        EmployeeInventoryProvider eip = EmployeeInventoryProvider.getInstance(getActivity());

        String[] projections = new String[]{EmployeeInventoryProvider.EmployeeContracts.ID, EmployeeInventoryProvider.EmployeeContracts.NAME};


        Cursor cursor = eip.query(EmployeeInventoryProvider.EmployeeContracts.CONTENT_URI, projections, null, null, null);

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{EmployeeInventoryProvider.EmployeeContracts.NAME},
                new int[]{android.R.id.text1},
                0);

        listView.setAdapter(cursorAdapter);

        listView.setOnItemClickListener(this);



        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = EditPersonActivity.newIntent(getActivity(), (int)id);
        startActivity(intent);

    }
}
