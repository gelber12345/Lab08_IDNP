package com.idnp.lab08.Activitys.Cliente;

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
import com.idnp.lab08.model.entidades.Cliente;
import com.idnp.lab08.model.DbCliente;
public class ClienteEdit extends AppCompatActivity {

    EditText etCorreo, etPassword,etUbicacion;
    Button btnGuarda,btnEliminar;
    int id;
    Cliente cliente;
    boolean correcto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_edit);

        etCorreo =findViewById(R.id.etCorreoEditCliente);
        etPassword =findViewById(R.id.etPasswordEditCliente);
        etUbicacion =findViewById(R.id.etUbicacionEditCliente);

        btnGuarda = findViewById(R.id.btnRegistrarEditCliente);
        btnEliminar = findViewById(R.id.btnEliminarEditCliente);


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

        final DbCliente dbCliente = new DbCliente(ClienteEdit.this);
        cliente = dbCliente.verCliente(id);

        if(cliente != null){
            etCorreo.setText(cliente.getCorreo());
            etPassword.setText(cliente.getPassword());
            etUbicacion.setText(cliente.getUbicacion());

        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCorreo.getText().toString().equals("") && !etPassword.getText().toString().equals("") && !etUbicacion.getText().toString().equals("") ) {
                    correcto = dbCliente.editarCliente(id,etCorreo.getText().toString(), etPassword.getText().toString(), etUbicacion.getText().toString());

                    if(correcto){
                        Toast.makeText(ClienteEdit.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        Regresar();
                    } else {
                        Toast.makeText(ClienteEdit.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ClienteEdit.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClienteEdit.this);
                builder.setMessage("¿Desea eliminar este Cliente?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (dbCliente.eliminarCliente(id)) {
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
        Intent intent = new Intent(this, MainCliente.class);;
        startActivity(intent);
        this.finish();
    }
}