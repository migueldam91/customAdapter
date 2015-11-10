package com.example.miguel.customarrayadapter.util;

import android.content.res.Resources;
import android.util.Log;

import com.example.miguel.customarrayadapter.Clases.Alumno;
import com.example.miguel.customarrayadapter.Clases.Asignatura;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Miguel on 10/31/2015.
 */
public class miparserJsonAsignaturas {
    public int numeroAsignaturas=0;
    public miparserJsonAsignaturas() {
        super();
    }

    //recursoRaw = R.raw.blablabla
    public String parsear(Resources resource, int recursoRaw) {

        InputStream is = resource.openRawResource(recursoRaw);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();
        Log.v("Resultado json", jsonString);
        return jsonString;
    }

    public void leerJsonString(String jsonString, ArrayList<Asignatura> asignaturas) {
        //Here you must replace these values for the ones you are parsing.
        String cod_asignatura="";
        String nombre="";
        String descripcion="";
        double nota=0.0;


        Asignatura asig;
        try {
            //jsonString is a string parsed chain of the alumnos.json file
            JSONObject job= new JSONObject(jsonString);
            JSONObject jobject = job.getJSONObject("Asignaturas");
            JSONArray jobjects = jobject.getJSONArray("Asignatura");
            for(int i=0; i<jobjects.length();i++){
                cod_asignatura= jobjects.getJSONObject(i).getString("cod_asignatura");
                nombre=jobjects.getJSONObject(i).getString("nombre");
                descripcion=jobjects.getJSONObject(i).getString("descripcion");
                nota=Double.valueOf(jobjects.getJSONObject(i).getString("nota"));

                Log.v("JSoneado cod_asignatura", cod_asignatura);
                Log.v("JSoneado nombre", nombre);
                Log.v("JSoneado descripcion", descripcion);
                Log.v("JSoneado nota", String.valueOf(nota));

                asig= new Asignatura(cod_asignatura,nombre,descripcion,nota);

                asignaturas.add(asig);
            numeroAsignaturas=jobjects.length();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
