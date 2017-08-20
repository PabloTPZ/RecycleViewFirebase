package com.example.firebaseejemplo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ivan on 2/8/2016.
 */
public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {
    private static List<Persona> items;
    private View v;

    public PersonaAdapter(List<Persona> items) {
        this.items = items;
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item

        public TextView nombre;
        public TextView apellido;
        public TextView ci;


        public PersonaViewHolder(final View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.iNombre);
            apellido = (TextView) v.findViewById(R.id.iApellido);
            ci=(TextView) v.findViewById(R.id.iCi);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonaViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.apellido.setText(items.get(i).getApellido());
        viewHolder.ci.setText(items.get(i).getCi());
    }
}
