package com.example.miguel.customarrayadapter.Clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Miguel on 10/31/2015.
 */
public class Asignatura {
    private String nombre,descripcion;
    private String cod_asignatura;
    private double nota;

    public Asignatura(){}

    public Asignatura(String cod_asignatura,String nombre, String descripcion,  double nota) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cod_asignatura = cod_asignatura;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCod_asignatura() {
        return cod_asignatura;
    }

    public void setCod_asignatura(String cod_asignatura) {
        this.cod_asignatura = cod_asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

}
