package com.idnp.lab08.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper  {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "sistema.db";
    public static final String TABLE_CLIENTES = "t_clientes";
    public static final String TABLE_VETERINARIAS = "t_veterinarias";
    public static final String TABLE_ALBERGUES = "t_albergues";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CLIENTES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "correo TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "ubicacion TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_VETERINARIAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "distrito TEXT NOT NULL," +
                "ubicacion TEXT NOT NULL," +
                "correo TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALBERGUES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "distrito TEXT NOT NULL," +
                "ubicacion TEXT NOT NULL," +
                "correo TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CLIENTES);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_VETERINARIAS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ALBERGUES);
        onCreate(sqLiteDatabase);

    }
}
