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

public class CargarAlumnoActivity extends AppCompatActivity {

    private EditText editTextNuevoNombre, editTextNuevaEdad;
    private Button btnCargarAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_alumno);

        editTextNuevoNombre = findViewById(R.id.editTextNuevoNombre);
        editTextNuevaEdad = findViewById(R.id.editTextNuevaEdad);
        btnCargarAlumno = findViewById(R.id.btnCargarAlumno);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.11:3000/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface api = retrofit.create(RetrofitInterface.class);

        btnCargarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuevoNombre = editTextNuevoNombre.getText().toString();
                int nuevaEdad = Integer.parseInt(editTextNuevaEdad.getText().toString());

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("nombre", nuevoNombre);
                requestBody.put("edad", nuevaEdad);

                Call<Alumno> call = api.crearAlumno(requestBody);
                call.enqueue(new Callback<Alumno>() {
                    @Override
                    public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                        if (response.code() == 201) {
                            Toast.makeText(CargarAlumnoActivity.this, "Alumno cargado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CargarAlumnoActivity.this, "Error al cargar alumno", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Alumno> call, Throwable t) {
                        Toast.makeText(CargarAlumnoActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
