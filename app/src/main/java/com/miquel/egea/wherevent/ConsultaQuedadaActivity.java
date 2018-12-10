package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_quedada);
        tituloview = findViewById(R.id.titleview);
        descripcionview = findViewById(R.id.descripcionview);
        fechaview = findViewById(R.id.fechaview);
        horaview = findViewById(R.id.horaview);
        ubicacionview = findViewById(R.id.ubicacionview);
        iconoview = findViewById(R.id.iconoview);

        Intent data = getIntent();
        if (data!=null){
            String titulo = data.getStringExtra("titulo");
            tituloview.setText(titulo);
            String ubicacion = data.getStringExtra("ubicacion");
            ubicacionview.setText(ubicacion);
            String descripcion = data.getStringExtra("descripcion");
            descripcionview.setText(descripcion);
            String hora = data.getStringExtra("hora");
            horaview.setText(hora);
            String fecha = data.getStringExtra("fecha");
            fechaview.setText(fecha);
            Integer tipoevento = data.getIntExtra("tipoevento",-1);
            iconoview.setImageResource(iconos[tipoevento]);
        }

    }
}
