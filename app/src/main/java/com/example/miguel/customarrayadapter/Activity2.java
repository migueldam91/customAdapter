package com.example.miguel.customarrayadapter;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguel.customarrayadapter.Clases.Alumno;
import com.example.miguel.customarrayadapter.Clases.Asignatura;
import com.example.miguel.customarrayadapter.util.miparserJson;
import com.example.miguel.customarrayadapter.util.miparserJsonAsignaturas;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Activity2 extends ActionBarActivity {
    private TextView tvNombre, tvEdad;
    private ImageView ivAlumno;
    miparserJsonAsignaturas mpja = null;
    miparserJson mpj = null;
    TextView tvc1,tvc2,tvc3,tvc4,tvMedia;
    TableLayout tl1;
    TableRow tr1,tr2,tr3,tr4,tr5;
    double media=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tvMedia= (TextView) findViewById(R.id.tvMedia);
        tvNombre= (TextView) findViewById(R.id.tvNombre2);
        tvEdad=(TextView) findViewById(R.id.tvEdad2);
        ivAlumno=(ImageView) findViewById(R.id.ivAlumno2);

        tvNombre.setText(bundle.getString("nombre"));
        tvEdad.setText(String.valueOf(bundle.getInt("edad")));
        String recurso = bundle.getString("photoId");
        Log.v("TagTag", recurso);
        ivAlumno.setImageResource(getResources().getIdentifier(recurso, "drawable", getPackageName()));
        Alumno a = sacarAlumno(bundle.getString("nombre"));
        ArrayList<Asignatura> as= a.getAsignaturas();
        //Construcción tabla
        tl1=new TableLayout(getApplicationContext());

        TableRow[] filas = {
                tr1 = (TableRow) findViewById(R.id.fila1),
                tr2 = (TableRow) findViewById(R.id.fila2),
                tr3 = (TableRow) findViewById(R.id.fila3),
                tr4 = (TableRow) findViewById(R.id.fila4),
                tr5 = (TableRow) findViewById(R.id.fila5)
        };
        for ( int i = 0; i<filas.length;i++){
            tl1.inflate(getApplicationContext(),R.layout.item_tabla,filas[i]);
            tvc1= (TextView) filas[i].findViewById(R.id.textView2);
            tvc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mens = ((TextView) v).getText().toString();
                    Toast.makeText(getApplicationContext(), mens, Toast.LENGTH_LONG).show();
                }
            });
            tvc2= (TextView) filas[i].findViewById(R.id.textView3);
            tvc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mens= ((TextView) v).getText().toString();
                    Toast.makeText(getApplicationContext(),mens,Toast.LENGTH_LONG).show();
                }
            });
            tvc3= (TextView) filas[i].findViewById(R.id.textView4);
            tvc3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mens= ((TextView) v).getText().toString();
                    Toast.makeText(getApplicationContext(),mens,Toast.LENGTH_LONG).show();
                }
            });
            tvc4= (TextView) filas[i].findViewById(R.id.textView5);
            tvc4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mens= ((TextView) v).getText().toString();
                    Toast.makeText(getApplicationContext(),mens,Toast.LENGTH_LONG).show();
                }
            });
            tvc1.setText(as.get(i).getCod_asignatura());
            tvc2.setText(as.get(i).getNombre());
            tvc3.setText(as.get(i).getDescripcion());
            tvc4.setText(String.valueOf(as.get(i).getNota()));
            media+=as.get(i).getNota();
        }
        media/=filas.length;
        tvMedia.setText("Nota media " + String.valueOf(media));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(getApplicationContext(),"LALALALA",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }
    public Alumno sacarAlumno(String nombreAlumno){
        Alumno alu = null;
        mpj = new miparserJson();
        ArrayList<Alumno>alumnos = new ArrayList<Alumno>();
        String jsonStrinj= mpj.parsear(getResources(),R.raw.alumnos);
        mpj.leerJsonString(jsonStrinj,alumnos);

        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        mpja= new miparserJsonAsignaturas();
        String jsonString= mpja.parsear(getResources(),R.raw.asignaturas);
        mpja.leerJsonString(jsonString, asignaturas);

        for(int i = 0; i<alumnos.size();i++){
            if (alumnos.get(i).getNombre().equals(nombreAlumno))
            {
                alu=alumnos.get(i);
                alumnos.get(i).setAsignaturas(asignaturas);
            }else{
                Log.v("No encontrado", nombreAlumno+" no encontrado");
            }
        }
        return alu;

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
