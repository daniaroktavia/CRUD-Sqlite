package com.example.crudsqlite.Database;

import android.provider.BaseColumns;

public class DbTable {
    static String TABLE_PEGAWAI = "pegawai";

    static final class PegawaiColumns implements BaseColumns{
        static String ID = "id";
        static String NAME = "name";
        static String BIRTHDATE = "birthDate";
        static String GENDER = "gender";
    }
}
