package com.example.api_simple_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerAlumnosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlumnos;
    private AlumnoAdapter alumnoAdapter;
    private List<Alumno> alumnosList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumnos);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerViewAlumnos = findViewById(R.id.recyclerViewAlumnos);
        recyclerViewAlumnos.setLayoutManager(new LinearLayoutManager(this));

        // Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance
        RetrofitInterface api =  retrofit.create(RetrofitInterface.class);

        alumnoAdapter = new AlumnoAdapter(alumnosList);
        recyclerViewAlumnos.setAdapter(alumnoAdapter);

        // Configurar el evento de "pull-to-refresh"
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Actualizar la lista de alumnos al deslizar hacia abajo
                obtenerAlumnosDesdeApi(api);
            }
        });

        // Obtener y mostrar la lista de alumnos
        obtenerAlumnosDesdeApi(api);
    }

    private void obtenerAlumnosDesdeApi(RetrofitInterface api) {
        Call<List<Alumno>> call = api.getAlumnos();

        call.enqueue(new Callback<List<Alumno>>() {
            @Override
            public void onResponse(Call<List<Alumno>> call, Response<List<Alumno>> response) {
                if (response.code() == 200) {
                    alumnosList = response.body(); // Actualiza la lista de alumnos
                    alumnoAdapter.actualizarLista(alumnosList);
                }
                // Detener la animación de "pull-to-refresh"
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Alumno>> call, Throwable t) {
                Log.d("response", String.valueOf(t));
                // Detener la animación de "pull-to-refresh"
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}
