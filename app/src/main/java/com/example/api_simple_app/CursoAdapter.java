package com.example.api_simple_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private List<Curso> cursos;

    public CursoAdapter(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void updateData(List<Curso> newCursos) {
        cursos.clear();
        cursos.addAll(newCursos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_item, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder.textViewNombreCurso.setText(curso.getNombre());
        holder.textViewHoras.setText("Horas: " + curso.getHoras()+ " hs");
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombreCurso, textViewHoras;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreCurso = itemView.findViewById(R.id.textViewNombreCursoInscrito);
            textViewHoras = itemView.findViewById(R.id.textViewHorasInscrito);
        }
    }
}
