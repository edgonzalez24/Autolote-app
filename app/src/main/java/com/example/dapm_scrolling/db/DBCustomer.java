package com.example.dapm_scrolling.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBCustomer extends SQLiteOpenHelper {

    public DBCustomer(@Nullable Context context, @Nullable String name,
                      @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table customers(" +
                "idvehicle integer primary key autoincrement," +
                "marca text not null," +
                "modelo text not null," +
                "color text not null," +
                "anio text not null," +
                "tipo text not null" +
                " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
