package com.idnp.lab08.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.idnp.lab08.model.entidades.Cliente;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbCliente extends DbHelper{
    Context context;

    public DbCliente(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarCliente(String correo, String password, String ubicacion) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("correo", correo);
            values.put("password", password);
            values.put("ubicacion", ubicacion);
            id = db.insert(TABLE_CLIENTES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Cliente> mostrarClientes() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Cliente> listaCliente = new ArrayList<>();
        Cliente cliente;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " ORDER BY correo ASC", null);

        if (cursorCliente.moveToFirst()) {
            do {
                cliente = new Cliente();
                cliente.setId(cursorCliente.getInt(0));
                cliente.setCorreo(cursorCliente.getString(1));
                cliente.setPassword(cursorCliente.getString(2));
                cliente.setUbicacion(cursorCliente.getString(3));
                listaCliente.add(cliente);
            } while (cursorCliente.moveToNext());
        }

        cursorCliente.close();

        return listaCliente;
    }

    public Cliente verCliente(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cliente cliente = null;
        Cursor cursorCliente;

        cursorCliente = db.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorCliente.moveToFirst()) {
            cliente = new Cliente();
            cliente.setId(cursorCliente.getInt(0));
            cliente.setCorreo(cursorCliente.getString(1));
            cliente.setPassword(cursorCliente.getString(2));
            cliente.setUbicacion(cursorCliente.getString(3));

        }

        cursorCliente.close();

        return cliente;
    }
    public boolean editarCliente(int id, String correo, String password, String ubicacion) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CLIENTES + " SET correo = '" + correo + "', password = '" + password + "', ubicacion = '" + ubicacion + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public boolean eliminarCliente(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CLIENTES + " WHERE id = '" + id + "'");
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
