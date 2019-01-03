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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ValorarAsistenciaActivity extends AppCompatActivity {

    private ArrayList<String> asisten;
    private MyAdapter adapter;
    private ArrayList<String> final_si_asisten;
    private ArrayList<String> final_no_asisten;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valorar_asistencia);
        Intent data = getIntent();
        asisten = (ArrayList<String>) data.getSerializableExtra("asistentes");
        final_si_asisten=new ArrayList<>();
        final_no_asisten= new ArrayList<>();
        RecyclerView mylist = findViewById(R.id.confirmar_view);
        mylist.setLayoutManager(new LinearLayoutManager(this));
        mylist.setAdapter(new MyAdapter());
    }

    public void Finalizar(View view) {
        Intent data = new Intent();
        data.putExtra("final_si_asisten",final_si_asisten);
        data.putExtra("final_no_asisten",final_no_asisten);
        setResult(RESULT_OK,data);
        finish();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox marcador;
        TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);
            this.marcador = itemView.findViewById(R.id.marcador);
            this.nombre = itemView.findViewById(R.id.nombreview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean existe =false;
                    //guardamos la info de quien ha venido y quien no al final
                    if(marcador.isChecked()) {
                        marcador.setChecked(false);
                        for(int i=0;i<final_si_asisten.size();i++) {
                            if (asisten.get(getAdapterPosition()).equals(final_si_asisten.get(i)))final_si_asisten.remove(i);
                        }
                        for(int i=0;i<final_no_asisten.size();i++){
                            if(asisten.get(getAdapterPosition()).equals(final_no_asisten.get(i))) existe =true;
                        }
                        if(!existe)final_no_asisten.add(asisten.get(getAdapterPosition()));
                    }
                    else
                        marcador.setChecked(true);
                        for(int i=0;i<final_no_asisten.size();i++) {
                            if (asisten.get(getAdapterPosition()).equals(final_no_asisten.get(i)))final_no_asisten.remove(i);
                        }
                        for(int i=0;i<final_si_asisten.size();i++){
                            if(asisten.get(getAdapterPosition()).equals(final_si_asisten.get(i))) existe =true;
                        }
                        if(!existe)final_si_asisten.add(asisten.get(getAdapterPosition()));

                }
            });
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
            holder.marcador.setChecked(true);
        }

        @Override
        public int getItemCount() {
            return asisten.size();
        }
    }
}
