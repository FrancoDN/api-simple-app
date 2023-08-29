package com.example.api_simple_app;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("alumnos")
    Call<List<Alumno>> getAlumnos();

    @GET("cursos")
    Call<List<Curso>> getCursos();

    @GET("alumnos/{id}")
    Call<Alumno> getAlumnoPorId(@Path("id") String alumnoId);

    @GET("cursos/{cursoId}")
    Call<Curso> getCursoPorId(@Path("cursoId") String cursoId);

    @POST("alumnos")
    Call<Alumno> crearAlumno(@Body Map<String, Object> body);

    @POST("cursos")
    Call<Curso> crearCurso(@Body Map<String, Object> body);

    @PUT("alumnos/{alumnoId}")
    Call<Alumno> actualizarAlumno(
            @Path("alumnoId") String alumnoId,
            @Body Map<String, Object> body
    );

    @DELETE("alumnos/{alumnoId}")
    Call<Void> eliminarAlumno(@Path("alumnoId") String alumnoId);



}

