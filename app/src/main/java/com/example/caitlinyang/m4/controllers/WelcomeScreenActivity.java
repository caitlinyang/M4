package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.controllers.LoginActivity;
import com.example.caitlinyang.m4.controllers.RegistrationActivity;

/**
 * WelcomeScreenActivity
 */
public class WelcomeScreenActivity extends AppCompatActivity {

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        // when the login button is clicked, it switches the view to the Login screen
        Button loginPageButton = findViewById(R.id.loginButton);
        loginPageButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        Button registrationPageButton = findViewById(R.id.registerButton);
        registrationPageButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
