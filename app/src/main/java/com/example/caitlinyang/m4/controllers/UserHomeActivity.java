package com.example.caitlinyang.m4.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.caitlinyang.m4.R;
import com.example.caitlinyang.m4.model.DataBase;
import com.example.caitlinyang.m4.model.DatabaseSingleton;

public class UserHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        TextView welcome = (TextView) findViewById(R.id.welcomeUser);
        welcome.setText("Welcome " + DatabaseSingleton.getInstance().getCurrentUser().getName());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.loclist:
                Intent main = new Intent(getBaseContext(), LocationScreenActivity.class);
                startActivity(main);
                break;
            case R.id.hp:
                Intent main2 = new Intent(getBaseContext(), UserHomeActivity.class);
                startActivity(main2);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
