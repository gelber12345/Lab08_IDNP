package com.idnp.lab08.Activitys.Adaptadores;
import com.idnp.lab08.model.entidades.Veterinarias;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.idnp.lab08.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.idnp.lab08.Activitys.Veterinarias.VeterinariasEdit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class VeterinariasAdapter extends RecyclerView.Adapter<VeterinariasAdapter.VeterinariaViewHolder> {
    ArrayList<Veterinarias> listaVeterinarias;
    ArrayList<Veterinarias> listaOriginal;

    public VeterinariasAdapter(ArrayList<Veterinarias> listaVeterinarias) {
        this.listaVeterinarias = listaVeterinarias;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaVeterinarias);
    }
    @NonNull
    @Override
    public VeterinariaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_veterinarias, null, false);
        return new VeterinariaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeterinariaViewHolder holder, int position) {
        holder.viewNombre.setText(listaVeterinarias.get(position).getNombre());
        holder.viewDistrito.setText(listaVeterinarias.get(position).getDistrito());
        holder.viewUbicacion.setText(listaVeterinarias.get(position).getUbicacion());
        holder.viewCorreo.setText(listaVeterinarias.get(position).getCorreo());
    }

    @Override
    public int getItemCount() {
        return listaVeterinarias.size();
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaVeterinarias.clear();
            listaVeterinarias.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Veterinarias> collecion = listaOriginal.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                                    || i.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                                    || i.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())
                                    || i.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaVeterinarias.clear();
                listaVeterinarias.addAll(collecion);
            } else {
                for (Veterinarias c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                        || c.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getUbicacion().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaVeterinarias.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class VeterinariaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewDistrito,viewUbicacion, viewCorreo;

        public VeterinariaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDistrito = itemView.findViewById(R.id.viewDistrito);
            viewUbicacion = itemView.findViewById(R.id.viewUbicacion);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VeterinariasEdit.class);
                    intent.putExtra("ID", listaVeterinarias.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
