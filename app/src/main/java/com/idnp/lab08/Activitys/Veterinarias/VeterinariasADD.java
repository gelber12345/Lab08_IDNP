package com.idnp.lab08.Activitys.Veterinarias;

import androidx.appcompat.app.AppCompatActivity;
import com.idnp.lab08.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.idnp.lab08.model.entidades.Veterinarias;
import com.idnp.lab08.model.DbVeterinarias;
public class VeterinariasADD extends AppCompatActivity {

    EditText txtNombre, txtDistrito,txtUbicacion, txtCorreo;
    Button btnGuarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinarias_add);

        txtNombre = findViewById(R.id.etNombre);
        txtDistrito = findViewById(R.id.etDistrito);
        txtUbicacion = findViewById(R.id.etUbicacion);
        txtCorreo = findViewById(R.id.etCorreo);

        btnGuarda = findViewById(R.id.btnRegistrar);
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtDistrito.getText().toString().equals("") && !txtUbicacion.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") ) {

                    DbVeterinarias dbVeterinarias = new DbVeterinarias(VeterinariasADD.this);
                    long id = dbVeterinarias.insertarVeterinaria(txtNombre.getText().toString(), txtDistrito.getText().toString(), txtUbicacion.getText().toString() ,txtCorreo.getText().toString());

                    if (id > 0) {
                        Toast.makeText(VeterinariasADD.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        Regresar();
                    } else {
                        Toast.makeText(VeterinariasADD.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(VeterinariasADD.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void Regresar(){
        Intent intent = new Intent(this, MainVeterinarias.class);;
        startActivity(intent);
        this.finish();
    }
}