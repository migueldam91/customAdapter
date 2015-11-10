package com.example.miguel.customarrayadapter.util;

import android.content.res.Resources;
import android.util.Log;

import com.example.miguel.customarrayadapter.Clases.Alumno;

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
 * Created by Miguel on 10/15/2015.
 */
public class miparserJson {

    public miparserJson() {
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

    public void leerJsonString(String jsonString, ArrayList<Alumno> alumnos) {
        //Here you must replace these values for the ones you are parsing.
        String nombre_val="";
        String edad_val="";
        int edad_valor=0;
        String photo_id="";

        Alumno alu;
        try {
            //jsonString is a string parsed chain of the alumnos.json file
            JSONObject job= new JSONObject(jsonString);
            JSONObject jobject = job.getJSONObject("Alumnos");
            JSONArray jobjects = jobject.getJSONArray("Alumno");
            for(int i=0; i<jobjects.length();i++){
                nombre_val= jobjects.getJSONObject(i).getString("nombre");
                edad_val=jobjects.getJSONObject(i).getString("edad");
                edad_valor=Integer.parseInt(edad_val);
                photo_id=jobjects.getJSONObject(i).getString("photoId");


                Log.v("JSoneado edad_val", edad_val);
                Log.v("JSoneado nombre_val", nombre_val);
                Log.v("JSoneado photoId", photo_id);

                alu= new Alumno(nombre_val,edad_valor,photo_id);
                //alu.setPhotoId(alu.getPhotoId().replace("img", ""));
                alumnos.add(alu);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



