package com.miquel.egea.wherevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class RankingActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String texto1;
    private String texto2;
    private Long maxrango = 0L;
    private Long minrango = 0L;
    private String maxuser = "";
    private String minuser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        final TextView rangos_view = findViewById(R.id.rango_view);
        final TextView nombre_view = findViewById(R.id.nombre_view);
        final TextView mejor_view = findViewById(R.id.mejorview);
        final TextView peor_view = findViewById(R.id.peorview);
        //recogemos los usuarios totales del firebase y sus rangos
        db.collection("Usuarios").orderBy("rango",Query.Direction.DESCENDING).addSnapshotListener(RankingActivity.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                int cont = 1;
                texto1 = "";
                texto2 = "";
                for (DocumentSnapshot doc : documentSnapshots) {
                    Usuario usuario = new Usuario(doc.getString("username"), doc.getString("usercode"), doc.getLong("rango"));
                    texto1 = texto1 + cont + ".  " + usuario.getUsername() +"\n\n";
                    texto2 = texto2 + "  " + usuario.getRango()+"\n\n";
                    nombre_view.setText(texto1);
                    rangos_view.setText(texto2);

                    if(cont==1){
                        maxrango = usuario.getRango();
                        minrango = usuario.getRango();
                        maxuser = usuario.getUsername();
                        minuser = usuario.getUsername();
                    }
                    cont++;
                    if(usuario.getRango()>maxrango){
                        maxrango = usuario.getRango();
                        maxuser = usuario.getUsername();
                    }
                    else if(usuario.getRango()<=minrango){
                        minrango=usuario.getRango();
                        minuser=usuario.getUsername();
                    }
                    if(maxuser==null)mejor_view.setText("");
                    else mejor_view.setText(maxuser);
                    if(minuser==null)peor_view.setText("");
                    else peor_view.setText(minuser);
                }

            }
        });


    }


}
