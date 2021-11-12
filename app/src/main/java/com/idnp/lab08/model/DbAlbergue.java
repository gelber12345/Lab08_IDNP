package com.idnp.lab08.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.idnp.lab08.model.entidades.Albergue;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbAlbergue extends DbHelper{
    Context context;

    public DbAlbergue(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarAlbergue(String nombre, String distrito, String ubicacion, String correo) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("distrito", distrito);
            values.put("ubicacion", ubicacion);
            values.put("correo", correo);
            id = db.insert(TABLE_ALBERGUES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Albergue> mostrarAlbergues() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Albergue> listaAlbergue = new ArrayList<>();
        Albergue albergue;
        Cursor cursorAlbergues;

        cursorAlbergues = db.rawQuery("SELECT * FROM " + TABLE_ALBERGUES + " ORDER BY nombre ASC", null);

        if (cursorAlbergues.moveToFirst()) {
            do {
                albergue = new Albergue();
                albergue.setId(cursorAlbergues.getInt(0));
                albergue.setNombre(cursorAlbergues.getString(1));
                albergue.setDistrito(cursorAlbergues.getString(2));
                albergue.setUbicacion(cursorAlbergues.getString(3));
                albergue.setCorreo(cursorAlbergues.getString(4));
                listaAlbergue.add(albergue);
            } while (cursorAlbergues.moveToNext());
        }

        cursorAlbergues.close();

        return listaAlbergue;
    }

    public Albergue verAlbergues(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Albergue albergue = null;
        Cursor cursorAlbergue;

        cursorAlbergue = db.rawQuery("SELECT * FROM " + TABLE_ALBERGUES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorAlbergue.moveToFirst()) {
            albergue = new Albergue();
            albergue.setId(cursorAlbergue.getInt(0));
            albergue.setNombre(cursorAlbergue.getString(1));
            albergue.setDistrito(cursorAlbergue.getString(2));
            albergue.setUbicacion(cursorAlbergue.getString(3));
            albergue.setCorreo(cursorAlbergue.getString(4));
        }

        cursorAlbergue.close();

        return albergue;
    }
    public boolean editarAlbergue(int id, String nombre, String distrito, String ubicacion, String correo) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_ALBERGUES + " SET nombre = '" + nombre + "', distrito = '" + distrito + "', ubicacion = '" + ubicacion + "', correo = '" + correo+ "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarAlbergue(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ALBERGUES + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
