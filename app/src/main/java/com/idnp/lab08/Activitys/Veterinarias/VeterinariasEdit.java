package com.idnp.lab08.Activitys.Veterinarias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.idnp.lab08.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.idnp.lab08.model.entidades.Veterinarias;
import com.idnp.lab08.model.DbVeterinarias;
public class VeterinariasEdit extends AppCompatActivity {

    EditText etNombre, etDistrito,etUbicacion,etCorreo;
    Button btnGuarda,btnEliminar;
    int id;
    Veterinarias veterinaria;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinarias_edit);

        etNombre =findViewById(R.id.etNombreEditVeter);
        etDistrito =findViewById(R.id.etDistritoEditVeter);
        etUbicacion =findViewById(R.id.etUbicacionEditVeter);
        etCorreo =findViewById(R.id.etCorreoEditVeter);
        btnGuarda = findViewById(R.id.btnRegistrarEditVeter);
        btnEliminar = findViewById(R.id.btnEliminarEditVeter);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbVeterinarias dbVeterinarias = new DbVeterinarias(VeterinariasEdit.this);
        veterinaria = dbVeterinarias.verVeterinaria(id);

        if(veterinaria != null){
            etNombre.setText(veterinaria.getNombre());
            etDistrito.setText(veterinaria.getDistrito());
            etUbicacion.setText(veterinaria.getUbicacion());
            etCorreo.setText(veterinaria.getCorreo());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etNombre.getText().toString().equals("") && !etDistrito.getText().toString().equals("") && !etUbicacion.getText().toString().equals("") && !etCorreo.getText().toString().equals("")) {
                    correcto = dbVeterinarias.editarVeterinaria(id,etNombre.getText().toString(), etDistrito.getText().toString(), etUbicacion.getText().toString() ,etCorreo.getText().toString());

                    if(correcto){
                        Toast.makeText(VeterinariasEdit.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        Regresar();
                    } else {
                        Toast.makeText(VeterinariasEdit.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VeterinariasEdit.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VeterinariasEdit.this);
                builder.setMessage("¿Desea eliminar esta veterinaria?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (dbVeterinarias.eliminarVeterinaria(id)) {
                                    Regresar();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

    }

    private void Regresar(){
        Intent intent = new Intent(this, MainVeterinarias.class);;
        startActivity(intent);
        this.finish();
    }
}