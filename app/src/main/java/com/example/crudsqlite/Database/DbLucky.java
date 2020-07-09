package com.example.crudsqlite.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DbLucky extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "DatabaseLucky";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_PEGAWAI = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DbTable.TABLE_PEGAWAI,
            DbTable.PegawaiColumns.ID,
            DbTable.PegawaiColumns.NAME,
            DbTable.PegawaiColumns.BIRTHDATE,
            DbTable.PegawaiColumns.GENDER
    );

    DbLucky(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PEGAWAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbTable.TABLE_PEGAWAI);
    }
}
