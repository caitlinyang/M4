package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
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

/**
 * LoginActivity
 */
public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    private HashMap<String, Object> users;

    /**
     * onCreate method
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        Button loginButton = findViewById(R.id.loginAccess);

        Log.d("TEST", "Login Activity reached");

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

        //Need to wait for database to populate first before clicking rn
        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View
             */
            @Override
            public void onClick(View v) {
                boolean found = checkRegistration() != null;
                if (found) {
                    Intent main = new Intent(getBaseContext(), UserHomeActivity.class);
                    main.putExtra("key", checkRegistration());
                    startActivity(main);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Cancel Button goes back to the welcome screen if cancel is hit
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onClick method
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Intent welcome = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                startActivity(welcome);
            }
        });

    }

    /**
     * checkRegistration method
     * @return Hashmap<String, Object>
     */
    private java.io.Serializable checkRegistration() {
        for (String key : users.keySet()) {
            HashMap<String, Object> user = (HashMap<String, Object>) users.get(key);
            if(username.getText().toString().trim().toLowerCase()
                    .equals(user.get("email")) && password.getText()
                    .toString().equals(user.get("password"))) {
                Log.d("TEST", "hi");
                return user;
            }
        }
        return null;
    }
}
