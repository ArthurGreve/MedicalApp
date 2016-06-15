package com.example.arthu.medicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arthu.medicalapp.Entity.Database;
import com.example.arthu.medicalapp.Entity.Doctor;
import com.example.arthu.medicalapp.Entity.Product;

import java.util.List;

public class DoctorActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        showDoctors();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Cadastrar Médico");
        menu.add(0, 1, 1, "Página inicial");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.showDoctors();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        switch(id) {
            case 0:
                intent= new Intent(this, DoctorEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 1:
                intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    private void showDoctors() {
        final ListView doctorList = (ListView)findViewById(R.id.doctorView);

        List<Doctor> values = MainActivity.getDb().getList(Doctor.class);

        ArrayAdapter<Doctor> studentAdapter =  new ArrayAdapter<Doctor>(this, android.R.layout.simple_list_item_1, values);

        doctorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Doctor doctor = (Doctor)doctorList.getItemAtPosition(position);

                Intent i = new Intent(view.getContext(), ProductEntryActivity.class);
                i.putExtra("Id", doctor.getId());
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        doctorList.setAdapter(studentAdapter);
    }
}
