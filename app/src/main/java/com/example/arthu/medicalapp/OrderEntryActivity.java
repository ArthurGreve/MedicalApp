package com.example.arthu.medicalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.arthu.medicalapp.ApiService.DoctorService;
import com.example.arthu.medicalapp.ApiService.HealthEnsuranceService;
import com.example.arthu.medicalapp.ApiService.OrderService;
import com.example.arthu.medicalapp.ApiService.ProcedureService;
import com.example.arthu.medicalapp.Entity.Doctor;
import com.example.arthu.medicalapp.Entity.HealthEnsurance;
import com.example.arthu.medicalapp.Entity.Order;
import com.example.arthu.medicalapp.Entity.Procedure;

public class OrderEntryActivity extends AppCompatActivity {

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_entry);

        Button btnSave = (Button) findViewById(R.id.btnSave);

        Doctor[] doctors = new DoctorService().getAll();
        Procedure[] procedures = new ProcedureService().getAll();
        HealthEnsurance[] healthEnsurances = new HealthEnsuranceService().getAll();

        final Spinner ddlDoctor = (Spinner) findViewById(R.id.ddlDoctor);
        final Spinner ddlProcedure = (Spinner) findViewById(R.id.ddlProcedure);
        final Spinner ddlHealthEnsurance = (Spinner) findViewById(R.id.ddlHealthEnsurance);

        ArrayAdapter<Doctor> adapDoctor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        ArrayAdapter<Procedure> adapProcedure = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, procedures);
        ArrayAdapter<HealthEnsurance> adapHealthEnsurance = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, healthEnsurances);

        adapDoctor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddlDoctor.setAdapter(adapDoctor);

        adapProcedure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddlProcedure.setAdapter(adapProcedure);

        adapHealthEnsurance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddlHealthEnsurance.setAdapter(adapHealthEnsurance);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doctor doctor = (Doctor) ddlDoctor.getSelectedItem();
                Procedure procedure = (Procedure) ddlProcedure.getSelectedItem();
                HealthEnsurance healthEnsurance = (HealthEnsurance) ddlHealthEnsurance.getSelectedItem();
                Order order = new Order(procedure, doctor, healthEnsurance);

                OrderService api = new OrderService();

                api.Add(order);

                finish();
            }
        });
    }
}
