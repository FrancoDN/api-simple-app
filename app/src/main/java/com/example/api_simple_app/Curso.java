package com.example.api_simple_app;

public class Curso {
    private String _id;
    private String nombre;
    private int horas;

    public Curso(String _id, String nombre, int horas) {
        this._id = _id;
        this.nombre = nombre;
        this.horas = horas;
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

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "_id='" + _id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", horas=" + horas +
                '}';
    }
}
