package com.example.caglarcoban.employeeinventory;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListEmployeeActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new ListEmployeeFragment();
    }
}
