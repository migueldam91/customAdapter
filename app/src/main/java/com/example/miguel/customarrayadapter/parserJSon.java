package com.example.miguel.customarrayadapter;

import android.util.JsonWriter;

import com.example.miguel.customarrayadapter.Clases.Alumno;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Miguel on 10/13/2015.
 */
public class parserJSon {
    public void writeJsonStream(OutputStream out, ArrayList<Alumno> alumnos) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        writeMessagesArray(writer, alumnos);
        writer.close();
    }

    public void writeMessagesArray(JsonWriter writer, ArrayList<Alumno> alumnos) throws IOException {
        writer.beginArray();
        for (Alumno alumno : alumnos) {
            writeMessage(writer, alumno);
        }
        writer.endArray();
    }

    public void writeMessage(JsonWriter writer, Alumno alumno) throws IOException {
        writer.beginObject();
        writer.name("nombre").value(alumno.getNombre());
        writer.name("edad").value(alumno.getEdad());
        if (alumno.getPhotoId()== null) {
            writer.name("photoId").value(alumno.getPhotoId());
        } else {
            writer.name("photoId").value(0);
        }
        writer.endObject();
    }

}
