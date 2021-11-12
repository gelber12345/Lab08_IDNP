package com.idnp.lab08.Activitys.Albergues;

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
import com.idnp.lab08.model.entidades.Albergue;
import com.idnp.lab08.model.DbAlbergue;
public class AlbergueEdit extends AppCompatActivity {

    EditText etNombre, etDistrito,etUbicacion,etCorreo;
    Button btnGuarda,btnEliminar;
    int id;
    Albergue albergue;
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albergue_edit);

        etNombre =findViewById(R.id.etNombreEditAlber);
        etDistrito =findViewById(R.id.etDistritoEditAlber);
        etUbicacion =findViewById(R.id.etUbicacionEditAlber);
        etCorreo =findViewById(R.id.etCorreoEditAlber);
        btnGuarda = findViewById(R.id.btnRegistrarEditAlber);
        btnEliminar = findViewById(R.id.btnEliminarEditAlber);


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

        final DbAlbergue dbAlbergue = new DbAlbergue(AlbergueEdit.this);
        albergue = dbAlbergue.verAlbergues(id);

        if(albergue != null){
            etNombre.setText(albergue.getNombre());
            etDistrito.setText(albergue.getDistrito());
            etUbicacion.setText(albergue.getUbicacion());
            etCorreo.setText(albergue.getCorreo());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etNombre.getText().toString().equals("") && !etDistrito.getText().toString().equals("") && !etUbicacion.getText().toString().equals("") && !etCorreo.getText().toString().equals("")) {
                    correcto = dbAlbergue.editarAlbergue(id,etNombre.getText().toString(), etDistrito.getText().toString(), etUbicacion.getText().toString() ,etCorreo.getText().toString());

                    if(correcto){
                        Toast.makeText(AlbergueEdit.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        Regresar();
                    } else {
                        Toast.makeText(AlbergueEdit.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AlbergueEdit.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlbergueEdit.this);
                builder.setMessage("¿Desea eliminar este Albergue?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (dbAlbergue.eliminarAlbergue(id)) {
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
        Intent intent = new Intent(this, MainAlbergue.class);;
        startActivity(intent);
        this.finish();
    }
}