package com.miquel.egea.wherevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaQuedadasActivity extends AppCompatActivity {

    private static final int NUEVA_QUEDADA = 0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView item_list;
    public Adapter adapter;
    List<Quedada> quedadas;
    private static int iconos[] = { R.drawable.bbq, R.drawable.bolos, R.drawable.camping, R.drawable.cena, R.drawable.cine, R.drawable.copa,
            R.drawable.estudio, R.drawable.globos, R.drawable.gym, R.drawable.pastel, R.drawable.playa, R.drawable.regalo,
            R.drawable.viaje};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quedadas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btn_newquedada = (FloatingActionButton) findViewById(R.id.btn_newquedada);
        btn_newquedada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaQuedadasActivity.this, NewQuedadaActivity.class);
                startActivityForResult(intent, NUEVA_QUEDADA);
                }
        });
        quedadas = new ArrayList<>();
        //conexion firebase
        /*db.collection("Quedadas").document("quedadas").addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                //noasistenview.setText(documentSnapshot.getDouble("NoAsisten"));
            }
        });*/
        //recorrer documentos creados en Firebase --> actualizar quedadas existentes (filtrar con el identificador = Id del documento) y crear(?) o eliminar las que no salgan o si en local

        //layout y adaptador RecyclerView
        item_list = findViewById(R.id.item_list);
        item_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter();
        item_list.setAdapter(adapter);
        item_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onStart() {
        super.onStart();

        db.collection("Quedadas").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                quedadas.clear();
                for (DocumentSnapshot doc : documentSnapshots) {
                    quedadas.add(new Quedada(doc.getString("Titulo"), "xxxx", "xxx", "000000", "0000", "autor", 0, 0));
                }
                adapter.notifyDataSetChanged();
            }
        });

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
    //FIN MENU 3 PUNTOS
    //recuperamos info de la creación de un evento nuevo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==NUEVA_QUEDADA){
            if (resultCode == RESULT_OK){
                Quedada nueva = new Quedada(data.getStringExtra("titulo"),
                        data.getStringExtra("descripcion"),
                        data.getStringExtra("ubicacion"),
                        data.getStringExtra("fecha"),
                        data.getStringExtra("hora"),
                        "yomismo",
                        3,
                        data.getIntExtra("tipoevento",-1)
                );
                db.collection("Quedadas").add(nueva).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("Wherevent", "He grabado la nueva quedada");
                    }
                });
                //adapter.notifyItemInserted(quedadas.size()-1);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //RECYCLER VIEW LISTA QUEDADAS*******************************************************************************************

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

            //añadimos un listener a los elementos quedadas
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEvento(getAdapterPosition());
                }
            });
        }
    }

    private void onClickEvento(int evento_position) {
        Intent intent = new Intent(this, ConsultaQuedadaActivity.class);
        intent.putExtra("titulo",quedadas.get(evento_position).getTitulo());
        intent.putExtra("ubicacion",quedadas.get(evento_position).getUbicacion());
        intent.putExtra("hora",quedadas.get(evento_position).getHora());
        intent.putExtra("fecha",quedadas.get(evento_position).getFecha());
        intent.putExtra("tipoevento",quedadas.get(evento_position).getTipo_evento());
        intent.putExtra("descripcion",quedadas.get(evento_position).getDescripción());
        startActivity(intent);
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
            holder.iconoview.setImageResource(iconos[model_item.getTipo_evento()]);
            holder.iconoview.setBackgroundColor(getResources().getColor(R.color.alta));
        }
        @Override
        public int getItemCount() {
            return quedadas.size();
        }
    }

//FIN RECYCLER VIEW LISTA QUEDADAS **************************************************************************************


}
