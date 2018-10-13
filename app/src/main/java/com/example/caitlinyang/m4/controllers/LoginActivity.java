package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.User;
import com.example.caitlinyang.m4.model.DatabaseSingleton;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText userEmail;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        Button loginButton = (Button) findViewById(R.id.loginAccess);

        userEmail = (EditText) findViewById(R.id.registration_email);
        userPassword = (EditText) findViewById(R.id.registrationpassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found = false;
                for (Object u : DatabaseSingleton.getInstance().getDb().getUserList()) {
                    if(username.getText().toString().trim().toLowerCase().equals(((User) u).getEmail()) && password.getText().toString().equals(((User) u).getPassword())) {
                        Intent main = new Intent(getBaseContext(), HomeScreenActivity.class);
                        startActivity(main);
                        found = true;
                    }
                }
                if (!found) {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }



            }
        });
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
