package com.example.arthu.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arthu.medicalapp.Entity.Database;
import com.example.arthu.medicalapp.Entity.Doctor;
import com.example.arthu.medicalapp.Entity.HealthEnsurance;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    private static Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new Database(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Produtos");
        menu.add(0, 1, 1, "Planos de Saúde");
        menu.add(0, 2, 2, "Médicos");
        menu.add(0, 3, 3, "Procedimentos");
        menu.add(0, 4, 4, "Pedidos");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent;
        switch(id) {
            case 0:
                intent= new Intent(this, ProductActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 1:
                intent = new Intent(this, HealthEnsuranceActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 2:
                intent = new Intent(this, DoctorActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 3:
                intent = new Intent(this, ProcedureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 4:
                intent = new Intent(this, OrderActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    public static Database getDb(){
        return db;
    }
}
