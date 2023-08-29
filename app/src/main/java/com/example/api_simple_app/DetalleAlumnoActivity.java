package com.example.api_simple_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleAlumnoActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextEdad;
    private Button btnGuardar, btnCursos, btnEliminarAlumno;
    private String alumnoId;

    private RecyclerView recyclerViewCursosInscritos;
    private CursoAdapter cursosAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alumno);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEdad = findViewById(R.id.editTextEdad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCursos = findViewById(R.id.btnCursos);
        btnEliminarAlumno = findViewById(R.id.btnEliminarAlumno);


        alumnoId = getIntent().getStringExtra("alumno_id");
        String nombreAlumno = getIntent().getStringExtra("alumno_nombre");
        int edadAlumno = getIntent().getIntExtra("alumno_edad", 0);

        editTextNombre.setText(nombreAlumno);
        editTextEdad.setText(String.valueOf(edadAlumno));

        recyclerViewCursosInscritos = findViewById(R.id.recyclerViewCursosInscritos);
        recyclerViewCursosInscritos.setLayoutManager(new LinearLayoutManager(this));

        cursosAdapter = new CursoAdapter(new ArrayList<>());
        recyclerViewCursosInscritos.setAdapter(cursosAdapter);

        // Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:3000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance
        RetrofitInterface api = retrofit.create(RetrofitInterface.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuevoNombre = editTextNombre.getText().toString();
                int nuevaEdad = Integer.parseInt(String.valueOf(editTextEdad.getText()));

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("nombre", nuevoNombre);
                requestBody.put("edad", nuevaEdad);

                Call<Alumno> cursosCall = api.actualizarAlumno(alumnoId, requestBody);
                cursosCall.enqueue(new Callback<Alumno>() {
                    @Override
                    public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                        if (response.code() == 200) {
                            Toast.makeText(DetalleAlumnoActivity.this, "Alumno actualizado", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Alumno> call, Throwable t) {
                        Log.d("response", String.valueOf(t));
                        Toast.makeText(DetalleAlumnoActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Alumno> cursosCall = api.getAlumnoPorId(alumnoId);
                cursosCall.enqueue(new Callback<Alumno>() {
                    @Override
                    public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                        if (response.code() == 200) {
                            Alumno alumnoActualizado = response.body();

                            // Obtener la lista de IDs de cursos del alumno
                            List<String> cursoIds = alumnoActualizado.getCursos();

                            // Inicializar la lista de cursos detallados
                            List<Curso> cursosDetalles = new ArrayList<>();

                            // Iterar a través de los IDs y obtener los detalles de cada curso
                            for (String cursoId : cursoIds) {
                                Call<Curso> cursoDetallesCall = api.getCursoPorId(cursoId);
                                cursoDetallesCall.enqueue(new Callback<Curso>() {
                                    @Override
                                    public void onResponse(Call<Curso> call, Response<Curso> response) {
                                        if (response.code() == 200) {
                                            Curso cursoDetalle = response.body();
                                            cursosDetalles.add(cursoDetalle);

                                            if (cursosDetalles.size() == cursoIds.size()) {
                                                cursosAdapter.updateData(cursosDetalles);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Curso> call, Throwable t) {
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Alumno> call, Throwable t) {
                        Log.d("response", String.valueOf(t));
                        Toast.makeText(DetalleAlumnoActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btnEliminarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> eliminarAlumnoCall = api.eliminarAlumno(alumnoId);
                eliminarAlumnoCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetalleAlumnoActivity.this, "Alumno eliminado", Toast.LENGTH_SHORT).show();
                            finish(); // Opcionalmente, regresar a la pantalla anterior
                        } else {
                            // Manejar respuesta de error
                            Toast.makeText(DetalleAlumnoActivity.this, "Error al eliminar alumno", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Manejar errores de conexión u otros errores
                        Toast.makeText(DetalleAlumnoActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
