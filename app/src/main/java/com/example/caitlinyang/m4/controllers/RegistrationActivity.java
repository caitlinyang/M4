package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.firebase.database.DatabaseError;
import android.support.annotation.NonNull;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * RegistrationActivity
 */
public class RegistrationActivity extends AppCompatActivity{

    private Spinner userTypeSpinner;

    private DatabaseReference mDatabase;

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private Map<String, Object> users;
    private static final List<String> userTypes = Arrays.asList("User",
            "Location Employee", "Admin");

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
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
        mDatabase.addValueEventListener(new ValueEventListener() {
            /**
             * onDataChange method
             * @param dataSnapshot DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> data = (HashMap<String, Object>) dataSnapshot.getValue();
                users = (HashMap<String, Object>) data.get("users");
            }

            /**
             * onCancelled method
             * @param databaseError DatabaseError
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
            /**
             * onClick method
             * @param v View
             */
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
                if (registerUser(email)) {
                    mDatabase.child("users").child(email).setValue(newUser);
                    Intent main = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                    startActivity(main);
                }
            }

            /**
             * registerUser method
             * @param email String
             * @return boolean true or false
             */
            public boolean registerUser(String email) {
                return users.containsKey(email);
            }
        });
    }



}
