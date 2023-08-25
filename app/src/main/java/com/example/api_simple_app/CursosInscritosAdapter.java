package com.example.api_simple_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CursosInscritosAdapter extends RecyclerView.Adapter<CursosInscritosAdapter.CursoViewHolder> {

    private List<Curso> cursosInscritos;

    public CursosInscritosAdapter(List<Curso> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }
    public void updateData(List<Curso> newCursosInscritos) {
        cursosInscritos.clear();
        cursosInscritos.addAll(newCursosInscritos);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_inscrito_item, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursosInscritos.get(position);
        holder.textViewNombreCurso.setText(curso.getNombre());
        holder.textViewDescripcion.setText("Horas: " + curso.getHoras() + " hs");
    }

    @Override
    public int getItemCount() {
        return cursosInscritos.size();
    }

    static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombreCurso, textViewDescripcion;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreCurso = itemView.findViewById(R.id.textViewNombreCursoInscrito);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcionInscrito);
        }
    }
}
