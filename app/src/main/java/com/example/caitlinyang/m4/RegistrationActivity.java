package com.example.caitlinyang.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


//        Button submitButton = (Button) findViewById(R.id.submitRegistration);
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        Button cancelButton = (Button) findViewById(R.id.cancelRegistration);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent welcome = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                startActivity(welcome);
            }
        });
    }
}
