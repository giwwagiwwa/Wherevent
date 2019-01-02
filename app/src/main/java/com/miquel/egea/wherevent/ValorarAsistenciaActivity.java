package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ValorarAsistenciaActivity extends AppCompatActivity {

    private ArrayList<String> asisten;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valorar_asistencia);
        Intent data = getIntent();
        asisten = (ArrayList<String>) data.getSerializableExtra("asistentes");
        RecyclerView mylist = findViewById(R.id.confirmar_view);
        mylist.setLayoutManager(new LinearLayoutManager(this));
        mylist.setAdapter(new MyAdapter());
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button asistio;
        Button noasistio;
        TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);
            this.asistio = itemView.findViewById(R.id.btn_si);
            this.noasistio = itemView.findViewById(R.id.btn_no);
            this.nombre = itemView.findViewById(R.id.nombreview);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater()
                    .inflate(R.layout.confirmacion_view,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.nombre.setText(asisten.get(position));
        }

        @Override
        public int getItemCount() {
            return asisten.size();
        }
    }
}
