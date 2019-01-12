package com.example.keeprawteach.legacydigital.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.keeprawteach.legacydigital.Calling.Calls;
import com.example.keeprawteach.legacydigital.Fragments.Account;
import com.example.keeprawteach.legacydigital.Fragments.Electronics;
import com.example.keeprawteach.legacydigital.Fragments.Home;
import com.example.keeprawteach.legacydigital.Fragments.Movies;
import com.example.keeprawteach.legacydigital.Fragments.Series;
import com.example.keeprawteach.legacydigital.Fragments.Watchlist;
import com.example.keeprawteach.legacydigital.R;
import com.example.keeprawteach.legacydigital.Settings.Sett;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent jeff=new Intent(this, Sett.class);

            startActivity(jeff);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

            setTitle("Home");

            Home log = new Home();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.Drawer, log, "Home");

            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {

            setTitle("Movies");

            Movies log = new Movies();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.Drawer, log, "Home");

            fragmentTransaction.commit();


        } else if (id == R.id.nav_slideshow) {

            setTitle("Series");

            Series log = new Series();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.Drawer, log, "Home");

            fragmentTransaction.commit();


        } else if (id == R.id.nav_manage) {

            setTitle("Electronics");

            Electronics log = new Electronics();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.Drawer, log, "Home");

            fragmentTransaction.commit();


        } else if (id == R.id.nav_account) {

            setTitle("Account");

            Account log = new Account();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.Drawer, log, "Home");

            fragmentTransaction.commit();


        } else if (id == R.id.nav_watch) {

            setTitle("Watchlist");

            Watchlist log = new Watchlist();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.Drawer, log, "Home");

            fragmentTransaction.commit();


        } else if (id == R.id.nav_contact) {

            Intent call = new Intent(getApplicationContext(), Calls.class);

            startActivity(call);

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
