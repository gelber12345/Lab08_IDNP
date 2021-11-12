package com.idnp.lab08.Activitys.Adaptadores;
import com.idnp.lab08.R;
import com.idnp.lab08.model.entidades.Albergue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.idnp.lab08.Activitys.Albergues.AlbergueEdit;
import com.idnp.lab08.model.entidades.Veterinarias;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlbergueAdapter extends RecyclerView.Adapter<AlbergueAdapter.AlbergueViewHolder>{
    ArrayList<Albergue> listaAlbergues;
    ArrayList<Albergue> listaOriginal;
    public AlbergueAdapter(ArrayList<Albergue> listaAlbergues) {
        this.listaAlbergues = listaAlbergues;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaAlbergues);
    }
    @NonNull
    @Override
    public AlbergueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_veterinarias, null, false);
        return new AlbergueAdapter.AlbergueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbergueViewHolder holder, int position) {
        holder.viewNombre.setText(listaAlbergues.get(position).getNombre());
        holder.viewDistrito.setText(listaAlbergues.get(position).getDistrito());
        holder.viewUbicacion.setText(listaAlbergues.get(position).getUbicacion());
        holder.viewCorreo.setText(listaAlbergues.get(position).getCorreo());
    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaAlbergues.clear();
            listaAlbergues.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Albergue> collecion = listaOriginal.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaAlbergues.clear();
                listaAlbergues.addAll(collecion);
            } else {
                for (Albergue c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaAlbergues.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listaAlbergues.size();
    }

    public class AlbergueViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewDistrito,viewUbicacion, viewCorreo;
        public AlbergueViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDistrito = itemView.findViewById(R.id.viewDistrito);
            viewUbicacion = itemView.findViewById(R.id.viewUbicacion);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AlbergueEdit.class);
                    intent.putExtra("ID", listaAlbergues.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
