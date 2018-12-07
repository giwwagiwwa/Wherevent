package com.miquel.egea.wherevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaQuedadasActivity extends AppCompatActivity {

    //private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView item_list;
    public android.widget.Adapter adapter;
    List<Quedada> quedadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quedadas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent = new Intent(this, NewQuedadaActivity.class);

        FloatingActionButton btn_newquedada = (FloatingActionButton) findViewById(R.id.btn_newquedada);
        btn_newquedada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(intent);
                }
        });

        //conexion firebase
        /*db.collection("Quedadas").document("quedadas").addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                titleview.setText(documentSnapshot.getString("Titulo"));
                autorview.setText(documentSnapshot.getString("Autor"));
                fechaview.setText(documentSnapshot.getString("Fecha"));
                horaview.setText(documentSnapshot.getString("Hora"));
                ubicacionview.setText(documentSnapshot.getString("Ubicacion"));
                asistenview.setText(Double.toString(documentSnapshot.getDouble("Asisten")));
                //noasistenview.setText(documentSnapshot.getDouble("NoAsisten"));
            }
        });
        */

        quedadas = new ArrayList<>();
        quedadas.add(new Quedada("ksdjfkj", "sdjflaskdjflaksdjf", "nose", "tsd", "sdf", "asdf", 1, 0));
        quedadas.add(new Quedada("ksdjfkj", "sdjflaskdjflaksdjf", "nose", "tsd", "sdf", "asdf", 2, 0));
        quedadas.add(new Quedada("ksdjfkj", "sdjflaskdjflaksdjf", "nose", "tsd", "sdf", "asdf", 3, 0));

        item_list = findViewById(R.id.item_list);
        item_list.setLayoutManager(new LinearLayoutManager(this));
        item_list.setAdapter(new Adapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_quedadas, menu);
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

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleview;
        TextView autorview;
        TextView fechaview;
        TextView horaview;
        TextView noasistenview;
        TextView asistenview;
        TextView ubicacionview;
        ImageView iconoview;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.titleview = itemView.findViewById(R.id.titleview);
            this.autorview = itemView.findViewById(R.id.autorview);
            this.fechaview = itemView.findViewById(R.id.fechaview);
            this.horaview = itemView.findViewById(R.id.horaview);
            this.noasistenview = itemView.findViewById(R.id.noasistenview);
            this.asistenview = itemView.findViewById(R.id.asistenview);
            this.ubicacionview = itemView.findViewById(R.id.ubicacionview);
            this.iconoview = itemView.findViewById(R.id.iconoview);
        }
    }

    class Adapter extends RecyclerView.Adapter<MyViewHolder> {

        //llenar lista manual

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Quedada model_item = quedadas.get(position);
            holder.titleview.setText(model_item.getTitulo());
            holder.autorview.setText(model_item.getAutor());
            holder.fechaview.setText(model_item.getFecha());
            holder.horaview.setText(model_item.getHora());
            holder.ubicacionview.setText(model_item.getUbicacion());
        }

        @Override
        public int getItemCount() {
            return quedadas.size();
        }

    }



}
