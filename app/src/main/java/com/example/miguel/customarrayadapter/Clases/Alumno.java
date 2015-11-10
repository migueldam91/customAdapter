package com.example.miguel.customarrayadapter.Clases;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by Miguel on 10/13/2015.
 */
public class Alumno {
    private String nombre,photoId;
    private int edad;
    private ArrayList<Asignatura> asignaturas;

    public Alumno(){};

    public Alumno(String nombre, String photoId, int edad, ArrayList<Asignatura> asignaturas) {
        this.nombre = nombre;
        this.photoId = photoId;
        this.edad = edad;
        this.asignaturas = asignaturas;
    }

    public Alumno(String nombre, int edad, String photo) {
        this.nombre = nombre;
        this.edad = edad;
        this.photoId = photo;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
