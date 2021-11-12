package com.idnp.lab08.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.idnp.lab08.model.entidades.Veterinarias;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbVeterinarias extends DbHelper {
    Context context;

    public DbVeterinarias(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarVeterinaria(String nombre, String distrito, String ubicacion, String correo) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("distrito", distrito);
            values.put("ubicacion", ubicacion);
            values.put("correo", correo);
            id = db.insert(TABLE_VETERINARIAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Veterinarias> mostrarVeterinarias() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Veterinarias> listaVeterinarias = new ArrayList<>();
        Veterinarias veterinarias;
        Cursor cursorVeterinarias;

        cursorVeterinarias = db.rawQuery("SELECT * FROM " + TABLE_VETERINARIAS + " ORDER BY nombre ASC", null);

        if (cursorVeterinarias.moveToFirst()) {
            do {
                veterinarias = new Veterinarias();
                veterinarias.setId(cursorVeterinarias.getInt(0));
                veterinarias.setNombre(cursorVeterinarias.getString(1));
                veterinarias.setDistrito(cursorVeterinarias.getString(2));
                veterinarias.setUbicacion(cursorVeterinarias.getString(3));
                veterinarias.setCorreo(cursorVeterinarias.getString(4));
                listaVeterinarias.add(veterinarias);
            } while (cursorVeterinarias.moveToNext());
        }

        cursorVeterinarias.close();

        return listaVeterinarias;
    }

    public Veterinarias verVeterinaria(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Veterinarias veterinarias = null;
        Cursor cursorVeterinarias;

        cursorVeterinarias = db.rawQuery("SELECT * FROM " + TABLE_VETERINARIAS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorVeterinarias.moveToFirst()) {
            veterinarias = new Veterinarias();
            veterinarias.setId(cursorVeterinarias.getInt(0));
            veterinarias.setNombre(cursorVeterinarias.getString(1));
            veterinarias.setDistrito(cursorVeterinarias.getString(2));
            veterinarias.setUbicacion(cursorVeterinarias.getString(3));
            veterinarias.setCorreo(cursorVeterinarias.getString(4));
        }

        cursorVeterinarias.close();

        return veterinarias;
    }
    public boolean editarVeterinaria(int id, String nombre, String distrito, String ubicacion, String correo) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_VETERINARIAS + " SET nombre = '" + nombre + "', distrito = '" + distrito + "', ubicacion = '" + ubicacion + "', correo = '" + correo+ "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarVeterinaria(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_VETERINARIAS + " WHERE id = '" + id + "'");
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
