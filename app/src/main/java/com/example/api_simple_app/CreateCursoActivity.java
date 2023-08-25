package com.example.api_simple_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateCursoActivity extends AppCompatActivity {

    private EditText editTextNombreCurso, editTextHorasCurso;
    private Button btnCrearCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_curso);

        editTextNombreCurso = findViewById(R.id.editTextNombreCurso);
        editTextHorasCurso = findViewById(R.id.editTextHorasCurso);
        btnCrearCurso = findViewById(R.id.btnCrearCurso);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:3000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface api = retrofit.create(RetrofitInterface.class);

        btnCrearCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreCurso = editTextNombreCurso.getText().toString();
                int horasCurso = Integer.parseInt(editTextHorasCurso.getText().toString());

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("nombre", nombreCurso);
                requestBody.put("horas", horasCurso);

                Call<Curso> call = api.crearCurso(requestBody);

                call.enqueue(new Callback<Curso>() {
                    @Override
                    public void onResponse(Call<Curso> call, Response<Curso> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CreateCursoActivity.this, "Curso creado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CreateCursoActivity.this, "Error al crear el curso", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Curso> call, Throwable t) {
                        Toast.makeText(CreateCursoActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
