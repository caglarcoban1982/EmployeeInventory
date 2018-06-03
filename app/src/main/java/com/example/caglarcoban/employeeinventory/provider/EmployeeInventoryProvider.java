package com.example.caglarcoban.employeeinventory.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.caglarcoban.employeeinventory.database.InventorySQLiteHelper;


public class EmployeeInventoryProvider extends ContentProvider{

    private static EmployeeInventoryProvider employeeInventoryProvider;
    private Context explicitContext = null;

    private InventorySQLiteHelper inventoryHelper;

    public static EmployeeInventoryProvider getInstance(Context context){

        if(employeeInventoryProvider == null){
            employeeInventoryProvider = new EmployeeInventoryProvider(context);
            return employeeInventoryProvider;
        }else{
            return employeeInventoryProvider;
        }


    }

    private EmployeeInventoryProvider(Context context){
        explicitContext = context;
        onCreate();
    }


    @Override
    public boolean onCreate() {

        if(explicitContext != null)
            inventoryHelper = new InventorySQLiteHelper(explicitContext);
        else
            inventoryHelper = new InventorySQLiteHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        String table = getTableNameFromUri(uri);



        try{
            SQLiteDatabase database = inventoryHelper.getReadableDatabase();
            cursor = database.query(table, projection, selection, selectionArgs, null, null, sortOrder);

            return cursor;
        }catch(SQLiteException e){
            Log.e("EmpInventoryProvider", "Database Error", e);
            e.printStackTrace();
            return null;
        }


    }

    private String getTableNameFromUri(Uri uri) {
        String path =  uri.getPath();
        if(path.startsWith("/"))
            return path.substring(1);
        else
            return path;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return EmployeeContracts.MIME_TYPE_EMPLOYEE;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getTableNameFromUri(uri);
        try{
            SQLiteDatabase database = inventoryHelper.getWritableDatabase();
            long rowId = database.insert(table, null, values);
            ContentUris contentUris = new ContentUris();
            Uri newUri = contentUris.withAppendedId(uri, rowId);
            return newUri;
        }catch(SQLiteException e){
            Log.e("EmpInventoryProvider", "Database Error", e);
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        String table = getTableNameFromUri(uri);
        try{
            SQLiteDatabase database = inventoryHelper.getWritableDatabase();
            int deletedCounts = database.delete(table, selection, selectionArgs);
            return deletedCounts;
        }catch(SQLiteException e){
            Log.e("EmpInventoryProvider", "Database Error", e);
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        String table = getTableNameFromUri(uri);
        try{
            SQLiteDatabase database = inventoryHelper.getWritableDatabase();
            int updatedrows = database.update(table, values, selection, selectionArgs);
            return updatedrows;
        }catch(SQLiteException e){
            Log.e("EmpInventoryProvider", "Database Error", e);
            e.printStackTrace();
            return -1;
        }

    }

    public static final class EmployeeContracts{

        public static final String AUTHORITY = "com.caglar.employeeinventory.provider";
        public static final String PATH = "employee";
        public static final String NAME = "name";
        public static final String SURNAME =  "surname";
        public static final String AGE = "age";
        public static final String MIME_TYPE_EMPLOYEE = "vnd.android.cursor.item/vnd.com.example.caglar.empenvtory.employee";
        public static final String ID = "_id";
        public static final String PHONE = "phone";
        public static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    }



}
