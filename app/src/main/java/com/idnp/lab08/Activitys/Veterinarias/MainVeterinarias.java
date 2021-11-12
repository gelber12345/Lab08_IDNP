package com.idnp.lab08.Activitys.Veterinarias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.idnp.lab08.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.idnp.lab08.model.entidades.Veterinarias;
import com.idnp.lab08.model.DbVeterinarias;
import java.util.ArrayList;
import com.idnp.lab08.Activitys.Adaptadores.VeterinariasAdapter;
public class MainVeterinarias extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView sv;
    RecyclerView rview;
    ArrayList<Veterinarias> listVeterinarias = new ArrayList<>();
    Button btnAdd;
    VeterinariasAdapter veterinariasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_veterinarias);
        sv = findViewById(R.id.buscarMainVeterinarias);
        rview = findViewById(R.id.listaVeterinarias);
        rview.setLayoutManager(new LinearLayoutManager(this));

        DbVeterinarias dbHelper = new DbVeterinarias (this);

        veterinariasAdapter = new VeterinariasAdapter(dbHelper.mostrarVeterinarias());

        rview.setAdapter(veterinariasAdapter);
        sv.setOnQueryTextListener(this);

        btnAdd = findViewById(R.id.btnMainVeterinariasAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAdd();
            }
        });
    }
    private void gotoAdd(){
        Intent intent = new Intent(this, VeterinariasADD.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        veterinariasAdapter.filtrado(s);
        return false;
    }
}