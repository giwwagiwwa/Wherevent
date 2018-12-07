package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewQuedadaActivity extends AppCompatActivity {
    EditText tituloedit;
    EditText ubicacionedit;
    EditText fechaedit;
    EditText horaedit;
    EditText descripcionedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quedada);
        tituloedit = this.findViewById(R.id.tituloedit);
        ubicacionedit = this.findViewById(R.id.ubicacionedit);
        fechaedit = this.findViewById(R.id.fechaedit);
        horaedit = this.findViewById(R.id.horaedit);
        descripcionedit = this.findViewById(R.id.descripcionedit);

    }

    public void onClickCrear(View view) {
        String titulo_edit = tituloedit.getText().toString();
        String ubicacion_edit = ubicacionedit.getText().toString();
        String fecha_edit = fechaedit.getText().toString();
        String hora_edit = horaedit.getText().toString();
        String descripcion_edit = descripcionedit.getText().toString();
        Intent data = new Intent();
        data.putExtra("titulo",titulo_edit);
        data.putExtra("ubicacion",ubicacion_edit);
        data.putExtra("fecha",fecha_edit);
        data.putExtra("hora",hora_edit);
        data.putExtra("descripcion",descripcion_edit);
        setResult(RESULT_OK,data);
        finish();

    }
}
