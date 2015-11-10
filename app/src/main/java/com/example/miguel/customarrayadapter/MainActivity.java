package com.example.miguel.customarrayadapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.miguel.customarrayadapter.Clases.Alumno;
import com.example.miguel.customarrayadapter.Clases.Asignatura;
import com.example.miguel.customarrayadapter.util.miparserJson;
import com.example.miguel.customarrayadapter.util.miparserJsonAsignaturas;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//Tarea: meter fotos en recursos y cargar una foto para cada alumno

public class MainActivity extends ActionBarActivity {
    public static int REQUEST_CODE= 1;
    miparserJson pjson; miparserJsonAsignaturas pjsonAsignaturas;
    Intent intent; Bundle bundle;
    TypedArray arrayIntegers;
    AlumnosAdapter alumnosAdapter;
    ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    Alumno alumno;
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "srXLNSEJ4UemTJWguK1mScxgyIgYFkEmNnJoEKfF", "I337ThBmJiz8GL2DGOGuJA7n0ydjFrqHzUAJb4Xj");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        ListView lv = (ListView) findViewById(R.id.listView);

        pjson = new miparserJson();
        String jsonString= pjson.parsear(getResources(),R.raw.alumnos);
        pjson.leerJsonString(jsonString, alumnos);
        /*//Set an arraylist of predetermined subjects to students
        setAsignaturas(alumnos);*/
        alumnosAdapter= new AlumnosAdapter(getApplicationContext(),0,alumnos);
        lv.setAdapter(alumnosAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Adapter", "Clickado" + position);
                alumno=alumnos.get(position);
                intent = new Intent(getApplicationContext(),Activity2.class);

                bundle= new Bundle();
                bundle.putString("nombre", alumno.getNombre());
                bundle.putString("photoId",alumno.getPhotoId());
                bundle.putInt("edad", alumno.getEdad());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class AlumnosAdapter extends ArrayAdapter<Alumno>{

    public AlumnosAdapter(Context context, int resource, List<Alumno> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Alumno alumno = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_alumno, parent,false);
        }
        TextView tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
        TextView tvEdad = (TextView) convertView.findViewById(R.id.tvEdad);
        ImageView ivFoto= (ImageView) convertView.findViewById(R.id.ivAlumno);
        TextView tvAsignaturas = (TextView) convertView.findViewById(R.id.tvNAsignaturas);

        tvNombre.setText(alumno.getNombre());
        tvEdad.setText(String.valueOf(alumno.getEdad()));
        String recurso = alumno.getPhotoId();
        ivFoto.setImageResource(getContext().getResources().getIdentifier(recurso, "drawable", getContext().getPackageName()));
        //Log.v("Numero asignaturas", String.valueOf(alumno.getAsignaturas().size()));
        //tvAsignaturas.setText("Nº Asignaturas: "+String.valueOf(alumno.getAsignaturas().size()));

        return convertView;



    }
}




