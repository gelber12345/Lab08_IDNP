package com.idnp.lab08.Activitys.Albergues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.idnp.lab08.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.idnp.lab08.model.entidades.Albergue;
import com.idnp.lab08.model.DbAlbergue;
import java.util.ArrayList;
import com.idnp.lab08.model.DbAlbergue;
import com.idnp.lab08.Activitys.Adaptadores.AlbergueAdapter;
public class MainAlbergue extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView sv;
    RecyclerView rview;
    ArrayList<Albergue> listAlbergues = new ArrayList<>();
    Button btnAdd;
    AlbergueAdapter albergueAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_albergue);
        sv = findViewById(R.id.buscarMainAlbergue);
        rview = findViewById(R.id.listaAlbergues);
        rview.setLayoutManager(new LinearLayoutManager(this));

        DbAlbergue dbHelper = new DbAlbergue (this);

        albergueAdapter = new AlbergueAdapter(dbHelper.mostrarAlbergues());

        rview.setAdapter(albergueAdapter);


        btnAdd = findViewById(R.id.btnMainAlbergueAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAdd();
            }
        });
        sv.setOnQueryTextListener(this);
    }
    private void gotoAdd(){
        Intent intent = new Intent(this, AlbergueAdd.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        albergueAdapter.filtrado(s);
        return false;
    }
}