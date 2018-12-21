package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private TextView autorview;

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
        autorview = findViewById(R.id.autorview);


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
            Long tipoevento = data.getLongExtra("tipoevento",-1);
            String sautorview = data.getStringExtra("autor");
            autorview.setText(sautorview);
            iconoview.setImageResource(iconos[(int)(long)tipoevento]);
        }

    }

    public void onClickAsistir(View view) {

    }

    public void onClickNoAsistir(View view) {

    }
}
