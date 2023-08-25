package com.example.api_simple_app;
import java.util.List;

public class Alumno {
    private String _id;
    private String nombre;
    private int edad;
    private List<String> cursos;

    public Alumno(String _id, String nombre, int edad, List<String> cursos) {
        this._id = _id;
        this.nombre = nombre;
        this.edad = edad;
        this.cursos = cursos;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

}
