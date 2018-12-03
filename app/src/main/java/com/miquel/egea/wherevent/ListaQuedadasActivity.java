package com.miquel.egea.wherevent;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ListaQuedadasActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


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

        Quedada quedada1 = new Quedada();
        final TextView titleview = findViewById(R.id.titleview);
        final TextView autorview = findViewById(R.id.autorview);
        final TextView fechaview = findViewById(R.id.fechaview);
        final TextView horaview = findViewById(R.id.horaview);
        final TextView noasistenview = findViewById(R.id.noasistenview);
        final TextView asistenview = findViewById(R.id.asistenview);
        final TextView ubicacionview = findViewById(R.id.ubicacionview);
        final ImageView iconoview = findViewById(R.id.iconoview);

        Glide.with(this).load("file:///drawable/bolos.png").into(iconoview); //pregunta


        db.collection("Quedadas").document("quedadas").addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                titleview.setText(documentSnapshot.getString("Titulo"));
                autorview.setText(documentSnapshot.getString("Autor"));
                fechaview.setText(documentSnapshot.getString("Fecha"));
                horaview.setText(documentSnapshot.getString("Hora"));
                ubicacionview.setText(documentSnapshot.getString("Ubicacion"));
                //asistenview.setText(documentSnapshot.getDouble("Asisten"); pregunta
                //noasistenview.setText(documentSnapshot.getString("NoAsisten"));
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


    //Recycler view

    class ViewHolder extends RecyclerView.ViewHolder{



        public ViewHolder(View itemView) {
            super(itemView);
        }
    }



}
