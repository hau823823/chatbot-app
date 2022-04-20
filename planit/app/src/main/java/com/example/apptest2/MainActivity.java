package com.example.apptest2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username" );
        String email = bundle.getString("email" );
        uId = String.valueOf(bundle.getInt("uId"));

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, ChatboxFragment.newInstance(uId)).commit();
            navigationView.setCheckedItem(R.id.nav_chatbox);
        }

        if(navigationView.getHeaderCount() > 0)
        {	View header = navigationView.getHeaderView(0);
            TextView un = (TextView) header.findViewById(R.id.mainUsername);
            un.setText(username);
            TextView ue = (TextView) header.findViewById(R.id.mainEmail);
            ue.setText(email);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_chatbox:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, ChatboxFragment.newInstance(uId)).commit();
                break;
            case R.id.nav_schedule:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, ScheduleFragment.newInstance(uId)).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, SearchFragment.newInstance(uId)).commit();
                break;
            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, FavoriteFragment.newInstance(uId)).commit();
                break;
            case R.id.nav_media:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MediaFragment()).commit();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(MainActivity.this,start.class);
                startActivity(intent);
                finish();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}