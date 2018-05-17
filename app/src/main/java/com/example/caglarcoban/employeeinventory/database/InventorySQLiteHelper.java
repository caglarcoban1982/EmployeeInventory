package com.example.caglarcoban.employeeinventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.caglarcoban.employeeinventory.provider.EmployeeInventoryProvider;

public class InventorySQLiteHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DB_NAME = "employeeinv.db";

    public InventorySQLiteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EmployeeInventoryProvider.EmployeeContracts.CONTENT + "(" +
                EmployeeInventoryProvider.EmployeeContracts.NAME + " text, "+
                EmployeeInventoryProvider.EmployeeContracts.SURNAME + " text, "+
                EmployeeInventoryProvider.EmployeeContracts.AGE + " int "+
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
