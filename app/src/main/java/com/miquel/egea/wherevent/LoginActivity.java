package com.miquel.egea.wherevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView usernameedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameedit = findViewById(R.id.usernameedit);

    }

    public void onClickCrear(View view) {
        String username = (String) usernameedit.getText();
        Intent intent = new Intent();
        intent.putExtra("username", username);
        setResult(RESULT_OK);

    }
}
