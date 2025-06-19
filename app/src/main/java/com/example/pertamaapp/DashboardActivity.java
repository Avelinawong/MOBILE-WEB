package com.example.pertamaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {
    LinearLayout llyProfil,llyTodolist;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        llyProfil= findViewById(R.id.llyProfil);
        llyTodolist=findViewById(R.id.llyToDoList);
        llyProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfil();
            }
        });
        llyTodolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTodolist();
            }
        });

    }

    public void toProfil(){
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.putExtra("nama","Avel");
        startActivity(intent);
    }

    public void toTodolist(){
        Intent intent = new Intent(this,ToDoListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Menutup aktivitas saat tombol "Back" ditekan
    }



}