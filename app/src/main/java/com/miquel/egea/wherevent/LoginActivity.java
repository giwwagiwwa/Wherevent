package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class LoginActivity extends AppCompatActivity {

    private TextView usernameedit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Usuario usuario;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameedit = findViewById(R.id.usernameedit);
    }

    public void onClickCrear(View view) {
        String username = usernameedit.getText().toString();
        usuario = new Usuario(username, "",0);
        saveUser();
        db.collection("Usuarios").add(usuario);
        setResult(RESULT_OK);
        finish();

    }

}
