package com.miquel.egea.wherevent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Quedada quedada1 = new Quedada();

        //referenciamos los objetos de la pantalla de prueba--> seran parte de la tarjeta del recycler view
        final TextView tituloview = findViewById(R.id.title_view);
        final TextView autorview = findViewById(R.id.autorview);
        final TextView ubicacionview = findViewById(R.id.ubicacionview);
        final TextView horaview = findViewById(R.id.horaview);
        final TextView fechaview = findViewById(R.id.fechaview);
        final TextView asistenview = findViewById(R.id.asistenview);
        final TextView noasistenview = findViewById(R.id.noasistenview);

        db.collection("Quedadas").document("quedadas").addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                autorview.setText(documentSnapshot.getString("Autor"));
                tituloview.setText(documentSnapshot.getString("Titulo"));
                ubicacionview.setText(documentSnapshot.getString("Ubicacion"));
                horaview.setText(documentSnapshot.getString("Hora"));
                fechaview.setText(documentSnapshot.getString("Fecha"));
                asistenview.setText(documentSnapshot.getString("Asisten"));
                noasistenview.setText(documentSnapshot.getString("NoAsisten"));
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
}
