package com.example.caitlinyang.m4.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.DataBase;
import com.example.caitlinyang.m4.model.User;

import java.util.Arrays;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity{

    private DataBase dataBase= new DataBase();
    private Spinner userTypeSpinner;
    private Button submitButton;
    private Button cancelButton;

    private EditText userName, userEmail, userPassword;
    public static List<String> userTypes = Arrays.asList("User", "Location Employee", "Admin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userTypeSpinner = (Spinner) findViewById(R.id.registrationusertype);
        // user type spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        // cancel button going back to Welcome Screen
        cancelButton = (Button) findViewById(R.id.cancelRegistration);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent welcome = new Intent(getBaseContext(), WelcomeScreenActivity.class);
                startActivity(welcome);
            }
        });

        submitButton = (Button) findViewById(R.id.submitRegistration);
        userName = (EditText)findViewById(R.id.registrationfullname);
        userEmail = (EditText) findViewById(R.id.registration_email);
        userPassword = (EditText) findViewById(R.id.registrationpassword);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName == null) {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();
                    alertDialog.setTitle("User's name is not entered");
                    alertDialog.setMessage("Please enter full name");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }

                if (userEmail == null) {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();
                    alertDialog.setTitle("User's email is not entered");
                    alertDialog.setMessage("Please enter email");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }

                if (userPassword == null) {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();
                    alertDialog.setTitle("User's password is not entered");
                    alertDialog.setMessage("Please enter password");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }

                final String name = userName.getText().toString().trim();
                final String email = userEmail.getText().toString().trim().toLowerCase();
                final String password = userPassword.getText().toString();
                final String userType = (String) userTypeSpinner.getSelectedItem();

                User newUser = new User(name, email, password, userType);

                dataBase.getUserList().add(newUser);
            }
        });
    }



}
