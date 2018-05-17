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
import android.widget.Toast;

import com.example.caglarcoban.employeeinventory.database.InventorySQLiteHelper;


public class EmployeeInventoryProvider extends ContentProvider{

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private InventorySQLiteHelper inventoryHelper;

    @Override
    public boolean onCreate() {
        inventoryHelper = new InventorySQLiteHelper(getContext());
        uriMatcher.addURI(EmployeeContracts.AUTHORITY, EmployeeContracts.PATH, 1);
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
            Toast.makeText(getContext(), "Database Unavailable", Toast.LENGTH_SHORT);
            e.printStackTrace();
            return null;
        }


    }

    private String getTableNameFromUri(Uri uri) {
        return uri.getPath();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
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
            Toast.makeText(getContext(), "Database Unavailable", Toast.LENGTH_SHORT);
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static final class EmployeeContracts{

        public static final String AUTHORITY = "com.caglar.employeeinventory.provider";
        public static final String PATH = "employee";
        public static final String NAME = "name";
        public static final String SURNAME =  "surname";
        public static final String AGE = "age";
        public static final String CONTENT_URI = "content://" + AUTHORITY + "/" + PATH;


    }



}
