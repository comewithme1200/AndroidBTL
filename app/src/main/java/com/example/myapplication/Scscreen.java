package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Scscreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView cvkho, cvingoods, cvoutgoods, cvmoney, cvstaff, cvcustomer;
    SharedPreferences prefs;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scscreen);
        prefs = getSharedPreferences("dataLogin", MODE_PRIVATE);
        //ánh xạ
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        cvkho = (CardView) findViewById(R.id.kho);
        cvingoods = (CardView) findViewById(R.id.hangnhap);
        cvoutgoods = (CardView) findViewById(R.id.hangxuat);
        cvmoney = (CardView) findViewById(R.id.thuchi);
        cvstaff = (CardView) findViewById(R.id.nhanvien);
        cvcustomer = (CardView) findViewById(R.id.khach);
        //setup toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Naving
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //menu items listener
        navigationView.setNavigationItemSelectedListener(this);
        //Listener
        cvkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scscreen.this, Qlkho.class);
                startActivity(intent);
            }
        });
        cvmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scscreen.this, Qlthuchi.class);
                startActivity(intent);
            }
        });
        cvstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scscreen.this, Qlnhanvien.class);
                startActivity(intent);
            }
        });
        cvcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Scscreen.this, Qlkhachhang.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("Thông báo").setMessage("Bạn có muốn thoát không?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        }


        //super.onBackPressed();
    }

    //Get actitivy when click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                Intent intent = new Intent(Scscreen.this, about_us.class);
                startActivity(intent);
                break;
            case R.id.contact:
                intent = new Intent(Scscreen.this, contact.class);
                startActivity(intent);
                break;
            case R.id.Logout:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("Thông báo").setMessage("Bạn có muốn đăng xuất không?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                flag = true;
//                                prefs.edit().remove("username").commit();
//                                prefs.edit().remove("password").commit();
                                Intent intent = new Intent(Scscreen.this, loginform.class);
                                Bundle mybundle = new Bundle();
                                mybundle.putBoolean("check", flag);
                                intent.putExtra("data", mybundle);
                                startActivity(intent);
                                dialog.dismiss();
                                finish();
                            }
                        });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.profile:
                intent = new Intent(Scscreen.this, user_profile.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}