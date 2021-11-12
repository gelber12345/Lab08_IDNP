package com.idnp.lab08.Activitys.Adaptadores;
import com.idnp.lab08.model.entidades.Cliente;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.idnp.lab08.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.idnp.lab08.Activitys.Cliente.ClienteEdit;
import com.idnp.lab08.model.entidades.Veterinarias;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>{
    ArrayList<Cliente> listaClientes;
    ArrayList<Cliente>listaOriginal;
    public ClienteAdapter(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaClientes);

    }
    @NonNull
    @Override
    public ClienteAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_veterinarias, null, false);
        return new ClienteAdapter.ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ClienteViewHolder holder, int position) {
        holder.viewCorreo.setText(listaClientes.get(position).getCorreo());
        holder.viewPassword.setText(listaClientes.get(position).getPassword());
        holder.viewUbicacion.setText(listaClientes.get(position).getUbicacion());

    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaClientes.clear();
            listaClientes.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Cliente> collecion = listaOriginal.stream()
                        .filter(i -> i.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getPassword().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaClientes.clear();
                listaClientes.addAll(collecion);
            } else {
                for (Cliente c : listaOriginal) {
                    if (c.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getPassword().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaClientes.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView viewCorreo, viewPassword,viewUbicacion;
        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewCorreo = itemView.findViewById(R.id.viewNombre);
            viewPassword = itemView.findViewById(R.id.viewDistrito);
            viewUbicacion = itemView.findViewById(R.id.viewUbicacion);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClienteEdit.class);
                    intent.putExtra("ID", listaClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
