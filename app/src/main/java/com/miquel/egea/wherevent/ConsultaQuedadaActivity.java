package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class ConsultaQuedadaActivity extends AppCompatActivity {
    public static final int VALORACION_OK = 0;
    private TextView tituloview;
    private TextView descripcionview;
    private TextView fechaview;
    private TextView horaview;
    private TextView ubicacionview;
    private static int iconos[] = { R.drawable.bbq, R.drawable.bolos, R.drawable.camping, R.drawable.cena, R.drawable.cine, R.drawable.copa,
            R.drawable.estudio, R.drawable.globos, R.drawable.gym, R.drawable.pastel, R.drawable.playa, R.drawable.regalo,
            R.drawable.viaje};
    private ImageView iconoview;
    private TextView autorview;
    private Usuario usuario;
    private TextView asistenview;
    private TextView noasistenview;
    ArrayList<Confirmacion> confirmacions;
    private ArrayList<String> asisten;
    private ArrayList<String> noAsisten;
    private TextView textrechazar;
    private TextView textaceptar;
    private Boolean ya_existe_user;
    private String asistenfinal;
    private String noasistenfinal;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String identificador_quedada;
    private String titulo;
    private String ubicacion;
    private String descripcion;
    private String hora;
    private String fecha;
    private String autor;
    private Long tipoevento;
    private Button valorar;
    private Button aceptar;
    private Button declinar;


    private void readUser() {
        try {
            FileInputStream inputStream = openFileInput("usuario.txt");
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                usuario = new Usuario(parts[0], parts[1], Long.getLong(parts[2]));
            }
        } catch (FileNotFoundException e) {
            Log.e("User", "No he podido abrir el fichero");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readUser();
        setContentView(R.layout.activity_consulta_quedada);
        tituloview = findViewById(R.id.titleview);
        descripcionview = findViewById(R.id.descripcionview);
        fechaview = findViewById(R.id.fechaview);
        horaview = findViewById(R.id.horaview);
        ubicacionview = findViewById(R.id.ubicacionview);
        iconoview = findViewById(R.id.iconoview);
        autorview = findViewById(R.id.autorview);
        asistenview = findViewById(R.id.asistenview);
        noasistenview = findViewById(R.id.noasistenview);
        textaceptar = findViewById(R.id.textaceptar);
        textrechazar = findViewById(R.id.textrechazar);
        valorar = findViewById(R.id.btn_valorar);
        aceptar = findViewById(R.id.aceptar_btn);
        declinar = findViewById(R.id.declinar_btn);
        boolean finalizado = false;

        Intent data = getIntent();
        if (data!=null){
            identificador_quedada = data.getStringExtra("identificador");
            titulo = data.getStringExtra("titulo");
            tituloview.setText(titulo);
            ubicacion = data.getStringExtra("ubicacion");
            ubicacionview.setText(ubicacion);
            descripcion = data.getStringExtra("descripción");
            descripcionview.setText(descripcion);
            hora = data.getStringExtra("hora");
            horaview.setText(hora);
            fecha = data.getStringExtra("fecha");
            fechaview.setText(fecha);
            autor = data.getStringExtra("autor");
            autorview.setText(autor);
            finalizado = data.getBooleanExtra("finalizado",false);

            tipoevento = data.getLongExtra("tipoevento",-1);
            //comprobamos si ha clicado en un icono
            if(tipoevento ==-1){
                iconoview.setImageResource(R.drawable.wherevent);
            }
            else iconoview.setImageResource(iconos[(int)(long) tipoevento]);

            confirmacions = (ArrayList<Confirmacion>) data.getSerializableExtra("confirmaciones");
            //recorremos la lista de confirmaciones y miramos si asisten o no

            asisten = new ArrayList<>();
            noAsisten = new ArrayList<>();
            for(int i=0; i<confirmacions.size();i++){
                    if(confirmacions.get(i).getConfirma()==0){ //no asisten
                        noAsisten.add(confirmacions.get(i).getCodigo_usuario());
                    }
                    else if(confirmacions.get(i).getConfirma()==1){ //asisten
                        asisten.add(confirmacions.get(i).getCodigo_usuario());
                    }
            }

            ActualizarDatos();
        }
        //comprobamos si la fecha es más reciente
        if(!finalizado){
            //comprobamos si eres el creador
            if(autor.equals(usuario.getUsername())) {
                valorar.setVisibility(View.VISIBLE);
            }
            else valorar.setVisibility(View.INVISIBLE);
            //si esta finalizado igualmente ocultamos los botones de votar
            aceptar.setVisibility(View.INVISIBLE);
            declinar.setVisibility(View.INVISIBLE);
        }
        else valorar.setVisibility(View.INVISIBLE);

    }

    public void onClickAsistir(View view) {
        ya_existe_user=false;
        //comprovem si l'usuari esta a la llista de no asisteixen
        for(int i=0; i<noAsisten.size();i++){
            if(noAsisten.get(i).equals(usuario.getUsername())) noAsisten.remove(i);//si estaba a l'altre llista l'eliminem
        }
        for(int i=0; i<asisten.size();i++){
            if(asisten.get(i).equals(usuario.getUsername())) ya_existe_user = true;
        }
        if(!ya_existe_user) asisten.add(usuario.getUsername()); //l'afegim a la llista correcta si no
        //Actualitzem la llista
        ActualizarDatos();

    }

    public void onClickNoAsistir(View view) {
        ya_existe_user=false;
        //comprovem si l'usuari esta a la llista de si asisteixen
        for(int i=0; i<asisten.size();i++){
            if(asisten.get(i).equals(usuario.getUsername()))asisten.remove(i);//si estaba a l'altre llista l'eliminem
        }
        for(int i=0; i<noAsisten.size();i++){ //mirem si ja estava inclos aqui
            if(noAsisten.get(i).equals(usuario.getUsername())) ya_existe_user=true;
        }
        if(!ya_existe_user) noAsisten.add(usuario.getUsername()); //l'afegim
        //Actualitzem la llista
        ActualizarDatos();
    }

    public void ActualizarDatos(){
        asistenview.setText("");
        noasistenview.setText("");
        Integer asisteni = asisten.size();
        Integer noasisteni = noAsisten.size();
        textaceptar.setText(asisteni.toString());
        textrechazar.setText(noasisteni.toString());
        asistenfinal="";
        noasistenfinal ="";
        for(int i = 0; i< asisten.size(); i++){
            asistenfinal = asistenfinal +asisten.get(i)+"\n\n";
            asistenview.setText(asistenfinal);
        }
        for(int i = 0; i< noAsisten.size(); i++){
            noasistenfinal = noasistenfinal+noAsisten.get(i)+"\n\n";
            noasistenview.setText(noasistenfinal);
        }

    }

    @Override
    protected void onStop() {
        ArrayList<Object> confirmaciones = new ArrayList<>();
        for(int i=0; i<asisten.size();i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("codigo_usuario",asisten.get(i));
            map.put("confirma",1L);
            confirmaciones.add(map);
        }
        for(int i=0; i<noAsisten.size();i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("codigo_usuario",noAsisten.get(i));
            map.put("confirma",0L);
            confirmaciones.add(map);
        }
        db.collection("Quedadas").document(identificador_quedada).update("confirmaciones",confirmaciones);
        super.onStop();
    }

    public void onClickValorar(View view) {
            //llamar a la actividad de valorar y mandar retorno para ocultar el botón!!
            Intent intent = new Intent(this, ValorarAsistenciaActivity.class);
            intent.putExtra("asistentes", asisten);
            startActivityForResult(intent, VALORACION_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            valorar.setVisibility(View.INVISIBLE);
            ArrayList<String> usercode_asisten = data.getStringArrayListExtra("usercode_asisten");
            ArrayList<String> usercode_no_asisten = data.getStringArrayListExtra("usercode_no_asisten");
            long[] rango_asisten = data.getLongArrayExtra("rango_asisten");
            long[] rango_no_asisten = data.getLongArrayExtra("rango_no_asisten");

            if(usercode_asisten.size()!=0) {
                for (int i = 0; i < usercode_asisten.size(); i++) {
                    Long rango = rango_asisten[i];
                    rango = rango + 5L;
                    db.collection("Usuarios").document(usercode_asisten.get(i)).update("rango", rango);
                }
            }
            if(usercode_no_asisten.size()!=0){
                for(int i=0; i<usercode_no_asisten.size();i++){
                    Long rango = rango_no_asisten[i];
                    rango = rango-5L;
                    db.collection("Usuarios").document(usercode_no_asisten.get(i)).update("rango", rango);
                }
            }
            Toast.makeText(ConsultaQuedadaActivity.this, "Puntuaciones actualizadas correctamente!", Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
