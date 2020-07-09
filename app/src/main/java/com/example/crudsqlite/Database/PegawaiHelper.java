package com.example.crudsqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.crudsqlite.Model.Pegawai;

import java.util.ArrayList;

import static com.example.crudsqlite.Database.DbTable.PegawaiColumns.BIRTHDATE;
import static com.example.crudsqlite.Database.DbTable.PegawaiColumns.GENDER;
import static com.example.crudsqlite.Database.DbTable.PegawaiColumns.ID;
import static com.example.crudsqlite.Database.DbTable.PegawaiColumns.NAME;
import static com.example.crudsqlite.Database.DbTable.TABLE_PEGAWAI;

public class PegawaiHelper {
    private static final String DATABASE_TABLE = TABLE_PEGAWAI;
    private static DbLucky dbLucky;
    private static PegawaiHelper INSTANCE;

    private static SQLiteDatabase database;

    private PegawaiHelper(Context context) {
        dbLucky = new DbLucky(context);
    }

    public static PegawaiHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PegawaiHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dbLucky.getWritableDatabase();
    }

    public void close() {
        dbLucky.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Pegawai> getAllPegawai() {
        ArrayList<Pegawai> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Pegawai pegawai;
        if (cursor.getCount() > 0) {
            do {
                pegawai = new Pegawai();
                pegawai.setId(cursor.getString(cursor.getColumnIndexOrThrow(ID)));
                pegawai.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                pegawai.setBirthDate(cursor.getString(cursor.getColumnIndexOrThrow(BIRTHDATE)));
                pegawai.setGender(cursor.getString(cursor.getColumnIndexOrThrow(GENDER)));
                arrayList.add(pegawai);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertPegawai(Pegawai pegawai) {
        ContentValues args = new ContentValues();
        args.put(ID, pegawai.getId());
        args.put(NAME, pegawai.getName());
        args.put(BIRTHDATE, pegawai.getBirthDate());
        args.put(GENDER, pegawai.getGender());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int updatePegawai(Pegawai pegawai) {
        ContentValues args = new ContentValues();
        args.put(NAME, pegawai.getName());
        args.put(BIRTHDATE, pegawai.getBirthDate());
        args.put(GENDER, pegawai.getGender());
        return database.update(DATABASE_TABLE, args, ID + "= '" + pegawai.getId() + "'", null);

    }

    public int deletePegawai(String id) {
        return database.delete(TABLE_PEGAWAI, ID + " = '" + id + "'", null);
    }

    public int deleteAllData() {
        return database.delete(TABLE_PEGAWAI, null, null);
    }
}
