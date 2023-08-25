package com.example.api_simple_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerAlumnosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlumnos;
    private AlumnoAdapter alumnoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumnos);

        recyclerViewAlumnos = findViewById(R.id.recyclerViewAlumnos);
        recyclerViewAlumnos.setLayoutManager(new LinearLayoutManager(this));

        // Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:3000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance
        RetrofitInterface api =  retrofit.create(RetrofitInterface.class);

        Call<List<Alumno>> call = api.getAlumnos();

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if(response.code() != 200) {
                    Toast.makeText(VerAlumnosActivity.this, "Revisar conexion", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Alumno> alumnos = response.body();
                alumnoAdapter = new AlumnoAdapter(alumnos);
                recyclerViewAlumnos.setAdapter(alumnoAdapter);
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Log.d("response", String.valueOf(t));
            }
        });
    }
}
