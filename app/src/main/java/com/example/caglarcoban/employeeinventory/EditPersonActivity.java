package com.example.caglarcoban.employeeinventory;



import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditPersonActivity extends SingleFragmentActivity {

    private static final String SELECTED_PERSON = "com.example.caglarcoban.employeeinventory.editpersonactivity.id";

    @Override
    public Fragment createFragment() {
        return new EditPersonFragment();
    }

    public static Intent newIntent(Context context , int id){
        Intent intent = new Intent(context, EditPersonActivity.class);
        intent.putExtra(SELECTED_PERSON, id);
        return intent;
    }
}
