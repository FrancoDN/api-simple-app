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

public class VerCursosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCursos;
    private CursoAdapter cursoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cursos);

        recyclerViewCursos = findViewById(R.id.recyclerViewCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));

        // Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance
        RetrofitInterface api =  retrofit.create(RetrofitInterface.class);

        Call<List<Curso>> call = api.getCursos();

        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                if(response.code() != 200) {
                    Toast.makeText(VerCursosActivity.this, "Revisar conexion", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Curso> cursos = response.body();
                cursoAdapter = new CursoAdapter(cursos);
                recyclerViewCursos.setAdapter(cursoAdapter);
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                Log.d("response", String.valueOf(t));
            }
        });
    }
}
