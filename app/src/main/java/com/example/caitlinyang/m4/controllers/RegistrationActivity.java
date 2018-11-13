package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Arrays;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity{

    private Spinner userTypeSpinner;

    private DatabaseReference mDatabase;

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private static final List<String> userTypes = Arrays.asList("User",
            "Location Employee", "Admin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button submitButton;
        Button cancelButton;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userTypeSpinner = findViewById(R.id.registration_user_type);
        // user type spinner
        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        // cancel button going back to Welcome Screen
        cancelButton = findViewById(R.id.cancelRegistration);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                startActivity(welcome);
            }
        });

        submitButton = findViewById(R.id.submitRegistration);
        userName = findViewById(R.id.registration_full_name);
        userEmail = findViewById(R.id.registration_email);
        userPassword = findViewById(R.id.registration_password);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(userName.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter a full name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("".equals(userEmail.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter an email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("".equals(userPassword.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter a password",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String name = userName.getText().toString().trim();
                final String email = userEmail.getText().toString().trim().toLowerCase();
                final String password = userPassword.getText().toString();
                final String userType = (String) userTypeSpinner.getSelectedItem();

                User newUser = new User(name, email, password, userType);
                registerUser(newUser, email);
            }

            public void registerUser(User newUser, String email) {
                mDatabase.child("users").child(email).setValue(newUser);
                Intent main = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                startActivity(main);
            }
        });
    }



}
