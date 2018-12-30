package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class ConsultaQuedadaActivity extends AppCompatActivity {
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


    private void readUser() {
        try {
            FileInputStream inputStream = openFileInput("user.txt");
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                usuario = new Usuario(parts[0], "");
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


        Intent data = getIntent();
        if (data!=null){
            String titulo = data.getStringExtra("titulo");
            tituloview.setText(titulo);
            String ubicacion = data.getStringExtra("ubicacion");
            ubicacionview.setText(ubicacion);
            String descripcion = data.getStringExtra("descripción");
            descripcionview.setText(descripcion);
            String hora = data.getStringExtra("hora");
            horaview.setText(hora);
            String fecha = data.getStringExtra("fecha");
            fechaview.setText(fecha);
            String sautorview = data.getStringExtra("autor");
            autorview.setText(sautorview);

            Long tipoevento = data.getLongExtra("tipoevento",-1);
            //comprobamos si ha clicado en un icono
            if(tipoevento==-1){
                iconoview.setImageResource(R.drawable.wherevent);
            }
            else iconoview.setImageResource(iconos[(int)(long)tipoevento]);

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


    }

    public void onClickAsistir(View view) {
        //comprovem si l'usuari esta a la llista de no asisteixen
        for(int i=0; i<noAsisten.size();i++){
            if(noAsisten.get(i).equals(usuario.getUsername())){
                //l'eliminem,l'afegim a l'altra llista i sortim del bucle
                noAsisten.remove(i);
                asisten.add(usuario.getUsername());
                break;
            }
        }
        //Actualitzem la llista
        ActualizarDatos();

    }

    public void onClickNoAsistir(View view) {
        //comprovem si l'usuari esta a la llista de no asisteixen
        for(int i=0; i<asisten.size();i++){
            if(asisten.get(i).equals(usuario.getUsername())){
                //l'eliminem,l'afegim a l'altra llista i sortim del bucle
                asisten.remove(i);
                noAsisten.add(usuario.getUsername());
                break;
            }
        }
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
        for(int i = 0; i< asisten.size(); i++){
            asistenview.setText(asisten.get(i)+"\n");
        }
        for(int i = 0; i< noAsisten.size(); i++){
            noasistenview.setText(noAsisten.get(i)+"\n");
        }
    }

    @Override
    protected void onStop() {
        //Mirar las listas locales de asistir y no asistir y llenar el array clase Confirmación para
        // subirlo al firebase. Se podría añadir un listener para ver si alguien más está modificando
        //su estado en el mismo momento que tu
        super.onStop();
    }
}
