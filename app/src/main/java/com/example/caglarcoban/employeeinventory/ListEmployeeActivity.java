package com.example.caglarcoban.employeeinventory;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.list_employee_fragment);
        if(fragment == null){
            ListEmployeeFragment listEmployeefragment = new ListEmployeeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.list_employee_fragment, listEmployeefragment);
            ft.commit();
        }

    }
}
