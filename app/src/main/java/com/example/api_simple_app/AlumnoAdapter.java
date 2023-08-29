package com.example.api_simple_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private List<Alumno> alumnos;

    public AlumnoAdapter(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_item, parent, false);
        return new AlumnoViewHolder(view);

    }

    public void actualizarLista(List<Alumno> nuevaLista) {
        alumnos.clear();
        alumnos.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        holder.textViewNombre.setText(alumno.getNombre());
        holder.textViewEdad.setText("Edad: " + alumno.getEdad() + " años");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Enviar detalles del alumno a la actividad DetalleAlumnoActivity
                Intent intent = new Intent(view.getContext(), DetalleAlumnoActivity.class);
                intent.putExtra("alumno_id", alumno.get_id()); // Envía el ID del alumno
                intent.putExtra("alumno_nombre", alumno.getNombre());
                intent.putExtra("alumno_edad", alumno.getEdad());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    static class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombre, textViewEdad;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewEdad = itemView.findViewById(R.id.textViewEdad);
        }
    }
}
