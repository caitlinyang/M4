package com.example.caitlinyang.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String username = ((EditText) findViewById(R.id.usernameInput)).getText().toString().trim().toLowerCase();
        String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
        Button loginButton = (Button) findViewById(R.id.loginAccess);


        // Cancel Button goes back to the welcome screen if cancel is hit
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent welcome = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                startActivity(welcome);
            }
        });

    }
}
