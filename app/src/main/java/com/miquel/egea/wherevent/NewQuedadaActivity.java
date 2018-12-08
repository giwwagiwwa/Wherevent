package com.miquel.egea.wherevent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NewQuedadaActivity extends AppCompatActivity {
    EditText tituloedit;
    EditText ubicacionedit;
    EditText fechaedit;
    EditText horaedit;
    EditText descripcionedit;
    private static int iconos[] = { R.drawable.bbq, R.drawable.bolos, R.drawable.camping, R.drawable.cena, R.drawable.cine, R.drawable.copa,
            R.drawable.estudio, R.drawable.globos, R.drawable.gym, R.drawable.pastel, R.drawable.playa, R.drawable.regalo,
            R.drawable.viaje,R.drawable.copa};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quedada);
        tituloedit = this.findViewById(R.id.tituloedit);
        ubicacionedit = this.findViewById(R.id.ubicacionedit);
        fechaedit = this.findViewById(R.id.fechaedit);
        horaedit = this.findViewById(R.id.horaedit);
        descripcionedit = this.findViewById(R.id.descripcionedit);

        RecyclerView mylist = findViewById(R.id.recyclerView2);
        //creamos el layout HORIZONTAL
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mylist.setLayoutManager(layoutManager);
        mylist.setAdapter(new MyAdapter());
    }
    //dar menos enfasis a los otros iconos(posible?)
    public void onClickIconSelect(View view) {

    }

    //recycler view iconos
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.image_view = itemView.findViewById(R.id.image_view);
        }

    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater()
                    .inflate(R.layout.icono_view,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.image_view.setImageResource(iconos[position]);
        }

        @Override
        public int getItemCount() {
            return iconos.length;
        }
    }

    //fin recycler view iconos

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
