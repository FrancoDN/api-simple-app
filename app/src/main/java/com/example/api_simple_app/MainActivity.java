package com.example.api_simple_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVerAlumnos = findViewById(R.id.btnVerAlumnos);
        Button btnVerCursos = findViewById(R.id.btnVerCursos);
        Button btnCargarAlumno = findViewById(R.id.btnCargarAlumno);
        Button btnCargarCurso = findViewById(R.id.btnCargarCurso);



        btnVerAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VerAlumnosActivity.class);
                startActivity(intent);
            }
        });

        btnVerCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VerCursosActivity.class);
                startActivity(intent);
            }
        });

        btnCargarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CargarAlumnoActivity.class);
                startActivity(intent);
            }
        });

        btnCargarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateCursoActivity.class);
                startActivity(intent);
            }
        });


    }

}