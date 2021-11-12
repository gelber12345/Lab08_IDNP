package com.idnp.lab08.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.idnp.lab08.model.DbHelper;
import com.idnp.lab08.Activitys.Veterinarias.MainVeterinarias;
import com.idnp.lab08.Activitys.Cliente.MainCliente;
import com.idnp.lab08.Activitys.Albergues.MainAlbergue;
import com.idnp.lab08.R;

public class MainActivity extends AppCompatActivity {

    Button btnVeterinarias,btnAlbergues,btnCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper (this);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        if ( db!= null){
            Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "BASE DE DATOS NO CREADA", Toast.LENGTH_LONG).show();
        }

        btnVeterinarias = findViewById(R.id.btnVeterinaria);

        btnVeterinarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVeterinaria();
            }
        });

        btnAlbergues = findViewById(R.id.btnAlbergue);

        btnAlbergues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAlbergues();
            }
        });
        btnCliente = findViewById(R.id.btnCliente);

        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToClientes();
            }
        });
    }

    public void goToVeterinaria(){
        Intent intent = new Intent(this, MainVeterinarias.class);
        startActivity(intent);
    }
    public void goToAlbergues(){
        Intent intent = new Intent(this, MainAlbergue.class);
        startActivity(intent);
    }
    public void goToClientes(){
        Intent intent = new Intent(this, MainCliente.class);
        startActivity(intent);
    }
}