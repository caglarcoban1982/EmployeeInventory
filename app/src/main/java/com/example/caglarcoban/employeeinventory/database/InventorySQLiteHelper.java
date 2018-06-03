package com.example.caglarcoban.employeeinventory.database;

import android.content.ContentValues;
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
        db.execSQL("create table " + EmployeeInventoryProvider.EmployeeContracts.PATH + "(" +
                EmployeeInventoryProvider.EmployeeContracts.ID + " integer primary key autoincrement, "+
                EmployeeInventoryProvider.EmployeeContracts.NAME + " text, "+
                EmployeeInventoryProvider.EmployeeContracts.SURNAME + " text, "+
                EmployeeInventoryProvider.EmployeeContracts.AGE + " int, "+
                EmployeeInventoryProvider.EmployeeContracts.PHONE + " text "+
                ")"
        );

        insertData(db,"caglar", "coban", 35, "053232323232");
        insertData(db,"pınar", "coban", 33, "054454444444");
        insertData(db,"serkan", "ozkan", 25, "06886888888");



    }

    private void insertData(SQLiteDatabase database, String name, String surname, int age, String phone) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(EmployeeInventoryProvider.EmployeeContracts.NAME, name);
            contentValues.put(EmployeeInventoryProvider.EmployeeContracts.SURNAME, surname);
            contentValues.put(EmployeeInventoryProvider.EmployeeContracts.AGE, age);
            contentValues.put(EmployeeInventoryProvider.EmployeeContracts.PHONE, phone);

            database.insert(EmployeeInventoryProvider.EmployeeContracts.PATH, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
