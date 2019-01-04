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
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    private TextView usernameedit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Usuario usuario;
    private ArrayList<Usuario> usuariosList;

    private void saveUser() {
        try {
            FileOutputStream outputStream = openFileOutput("usuario.txt", MODE_APPEND);
            String line = String.format("%s;%b;%s\n", usuario.getUsername(), usuario.getUsercode(),usuario.getRango());
            outputStream.write(line.getBytes());

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se ha podido cargar el usuario", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se ha podido guardar el usuario", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameedit = findViewById(R.id.usernameedit);
        usuariosList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.collection("Usuarios").addSnapshotListener(LoginActivity.this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                for (DocumentSnapshot doc : documentSnapshots) {
                    Usuario usuario = new Usuario(doc.getString("username"), doc.getString("usercode"), doc.getLong("rango"));
                    usuariosList.add(usuario);
                }
            }
        });
    }

    public void onClickCrear(View view) {
        String username = usernameedit.getText().toString();
        boolean ya_existe_user = false;
        //comprobamos si no existe el usuario
        for(int i=0; i<usuariosList.size();i++){
            if(username.equals(usuariosList.get(i).getUsername())) ya_existe_user=true;
        }
        if(ya_existe_user) Toast.makeText(LoginActivity.this, "Este nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
        else {
            usuario = new Usuario(username, "", 0L);
            saveUser();
            db.collection("Usuarios").add(usuario);
            setResult(RESULT_OK);
            finish();
        }

    }

}
