package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caitlinyang.m4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText userEmail;
    EditText userPassword;
    private DatabaseReference mDatabase;
    HashMap<String, Object> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        Button loginButton = (Button) findViewById(R.id.loginAccess);

        userEmail = (EditText) findViewById(R.id.registration_email);
        userPassword = (EditText) findViewById(R.id.registrationpassword);

        Log.d("TESTTEST", "Login Activity reached");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Object> data = (HashMap<String, Object>) dataSnapshot.getValue();
                Log.d("TEST", data.get("users").toString());
                users = (HashMap<String, Object>) data.get("users");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found = false;
                for (String key : users.keySet()) {
                    HashMap<String, Object> user = (HashMap<String, Object>) users.get(key);
                    if(username.getText().toString().trim().toLowerCase().equals(user.get("email")) && password.getText().toString().equals(user.get("password"))) {
                        Log.d("TEST", "Yeet");
                         if (user.get("userType").equals("User")) {
                            Intent main = new Intent(getBaseContext(), UserHomeActivity.class);
                            main.putExtra("key", user);
                            startActivity(main);
                        } else if (user.get("userType").equals("Location Employee")) {
                            Intent main2 = new Intent(getBaseContext(), LocEmployeeActivity.class);
                            main2.putExtra("key", user);
                            startActivity(main2);
                        }
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
