package com.idnp.lab08.Activitys.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.idnp.lab08.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.idnp.lab08.model.entidades.Cliente;
import com.idnp.lab08.model.DbCliente;
import java.util.ArrayList;
import com.idnp.lab08.Activitys.Adaptadores.ClienteAdapter;
public class MainCliente extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView sv;
    RecyclerView rview;
    ArrayList<Cliente> listClientes = new ArrayList<>();
    Button btnAdd;
    ClienteAdapter clienteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);
        sv = findViewById(R.id.buscarMainCliente);
        rview = findViewById(R.id.listaClientes);
        rview.setLayoutManager(new LinearLayoutManager(this));

        DbCliente dbHelper = new DbCliente (this);

        clienteAdapter = new ClienteAdapter(dbHelper.mostrarClientes());

        rview.setAdapter(clienteAdapter);


        btnAdd = findViewById(R.id.btnMainClienteAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAdd();
            }
        });
        sv.setOnQueryTextListener(this);
    }
    private void gotoAdd(){
        Intent intent = new Intent(this, ClienteAdd.class);
        startActivity(intent);
        this.finish();
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        clienteAdapter.filtrado(s);
        return false;
    }
}