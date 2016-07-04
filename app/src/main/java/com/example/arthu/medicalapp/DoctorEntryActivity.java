package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arthu.medicalapp.ApiService.DoctorService;
import com.example.arthu.medicalapp.Entity.Doctor;

public class DoctorEntryActivity extends AppCompatActivity {

    private long Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_entry);

        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnSave = (Button) findViewById(R.id.btnSave);

        if (this.getIntent().hasExtra("Id")) {
            this.Id = this.getIntent().getLongExtra("Id", -1);
            this.showFields(this.Id);
        }else{
            btnDelete.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtId = (EditText) findViewById(R.id.idField);
                EditText txtName = (EditText) findViewById(R.id.nameField);
                EditText txtSpecialization = (EditText)findViewById(R.id.specializatinoField);

                if (!EntryHelper.isFieldsEmpty(txtName, txtSpecialization)) {
                    Doctor doctor;
                    String name = txtName.getText().toString();
                    String specialization = txtSpecialization.getText().toString();

                    DoctorService api = new DoctorService();

                    if (TextUtils.isEmpty(txtId.getText())) {
                        doctor = new Doctor(name, specialization);
                        api.Add(doctor);
                    }else {
                        doctor = new Doctor(name, specialization);
                        doctor.setId(Long.parseLong(txtId.getText().toString()));
                        api.Update(doctor);
                    }

                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText idField = (EditText) findViewById(R.id.idField);

                if(!EntryHelper.isFieldsEmpty(idField)){
                    DoctorService api = new DoctorService();
                    api.Delete(idField.getText().toString());
                    finish();
                }
            }
        });
    }

    private void showFields(long id) {
        DoctorService api = new DoctorService();
        Doctor doctor = api.getById(id);

        EditText txtId = (EditText)findViewById(R.id.idField);
        EditText txtName = (EditText)findViewById(R.id.nameField);
        EditText txtSpecialization = (EditText)findViewById(R.id.specializatinoField);

        txtId.setText(Long.toString(id));
        txtName.setText(doctor.getName());
        txtSpecialization.setText(doctor.getSpecialization());
    }
}
