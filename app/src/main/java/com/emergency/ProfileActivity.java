package com.emergency;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        deklarasiWidget();
        setToolbar();
        setNavigationView();
    }
    private void setNavigationView() {
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });


        navigationView.setNavigationItemSelectedListener(naviItemSelect);

    }
    NavigationView.OnNavigationItemSelectedListener naviItemSelect=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setCheckable(true);//membuat tanda hitam setelah di click
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (menuItem.getItemId()){
                case R.id.home:
                    //cara memanggil ke halaman lain
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    Snackbar.make(getCurrentFocus(),"Anda Memilih Home",Snackbar.LENGTH_LONG).show();
                    return true;
                case R.id.signout:
                    Snackbar.make(getCurrentFocus(),"Anda Memilih Profile",Snackbar.LENGTH_LONG).show();
                    Intent i1=new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.address:
                    Snackbar.make(getCurrentFocus(),"Anda Memilih Address",Snackbar.LENGTH_LONG).show();
                    Intent i2=new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(i2);
                    return true;
                default:return true;
            }
        }
    };
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");//untuk get title nya di kosongin
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void deklarasiWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);


    }
}
