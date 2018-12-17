package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jonathanfinerty.once.Once;

public class LoginActivity extends AppCompatActivity {

    private TextView usernameedit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Once.initialise(this);
        String tag = "tag";
        if(!Once.beenDone(Once.THIS_APP_INSTALL,tag)){
            Once.markDone(tag);
        }
        else RegistrarUsuario();

        setContentView(R.layout.activity_login);
        usernameedit = findViewById(R.id.usernameedit);

    }

    private void RegistrarUsuario() {
        Intent intent = new Intent(LoginActivity.this, ListaQuedadasActivity.class);
        startActivity(intent);
    }

    private void saveUser() {
        try {
            FileOutputStream outputStream = openFileOutput("items.txt", MODE_PRIVATE);
                String line = String.format("%s;%b\n", usuario.getUsername(), usuario.getUsercode());
                outputStream.write(line.getBytes());

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se ha podido abrir el fichero", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se ha podido escribir", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCrear(View view) {
        String username = usernameedit.getText().toString();
        usuario = new Usuario(username, "");
        db.collection("Usuarios").add(usuario);
        saveUser();
        Intent intent = new Intent(LoginActivity.this,ListaQuedadasActivity.class);
        startActivity(intent);

    }
}
