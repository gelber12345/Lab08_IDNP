package com.idnp.lab08.Activitys.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import com.idnp.lab08.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.idnp.lab08.model.entidades.Cliente;
import com.idnp.lab08.model.DbCliente;
public class ClienteAdd extends AppCompatActivity {
    EditText txtCorreo, txtPassword,txtUbicacion ;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_add);

        txtCorreo = findViewById(R.id.etCorreoClienteADD);
        txtPassword = findViewById(R.id.etPasswordClienteADD);
        txtUbicacion = findViewById(R.id.etUbicacionClienteADD);


        btnGuarda = findViewById(R.id.btnRegistrarClienteADD);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtCorreo.getText().toString().equals("") && !txtPassword.getText().toString().equals("") && !txtUbicacion.getText().toString().equals("")  ) {

                    DbCliente dbCliente = new DbCliente(ClienteAdd.this);
                    long id = dbCliente.insertarCliente(txtCorreo.getText().toString(), txtPassword.getText().toString(), txtUbicacion.getText().toString());

                    if (id > 0) {
                        Toast.makeText(ClienteAdd.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        Regresar();
                    } else {
                        Toast.makeText(ClienteAdd.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ClienteAdd.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void Regresar(){
        Intent intent = new Intent(this, MainCliente.class);;
        startActivity(intent);
        this.finish();
    }
}