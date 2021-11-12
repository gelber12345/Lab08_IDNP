package com.idnp.lab08.Activitys.Albergues;

import androidx.appcompat.app.AppCompatActivity;
import com.idnp.lab08.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.idnp.lab08.model.DbAlbergue;
public class AlbergueAdd extends AppCompatActivity {

    EditText txtNombre, txtDistrito,txtUbicacion, txtCorreo;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albergue_add);

        txtNombre = findViewById(R.id.etNombreAlbergueAdd);
        txtDistrito = findViewById(R.id.etDistritoAlbergueAdd);
        txtUbicacion = findViewById(R.id.etUbicacionAlbergueAdd);
        txtCorreo = findViewById(R.id.etCorreoAlbergueAdd);

        btnGuarda = findViewById(R.id.btnRegistrarAlbergueAdd);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtDistrito.getText().toString().equals("") && !txtUbicacion.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") ) {

                    DbAlbergue dbAlbergue = new DbAlbergue(AlbergueAdd.this);
                    long id = dbAlbergue.insertarAlbergue(txtNombre.getText().toString(), txtDistrito.getText().toString(), txtUbicacion.getText().toString() ,txtCorreo.getText().toString());

                    if (id > 0) {
                        Toast.makeText(AlbergueAdd.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        Regresar();
                    } else {
                        Toast.makeText(AlbergueAdd.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AlbergueAdd.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void Regresar(){
        Intent intent = new Intent(this, MainAlbergue.class);;
        startActivity(intent);
        this.finish();
    }
}