package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arthu.medicalapp.Entity.Doctor;

public class DoctorEntryActivity extends AppCompatActivity {

    private long Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_entry);

        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnSave = (Button) findViewById(R.id.btnSave2);

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

                    if (TextUtils.isEmpty(txtId.getText())) {
                        doctor= new Doctor(name, specialization);
                    }else {
                        doctor = MainActivity.getDb().getEntityById(Doctor.class, Long.parseLong(txtId.getText().toString()));
                        doctor.Name = name;
                        doctor.Specialization = specialization;
                    }

                    MainActivity.getDb().save(doctor);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText idField = (EditText) findViewById(R.id.idField);

                if(!EntryHelper.isFieldsEmpty(idField)){
                    MainActivity.getDb().delete(Doctor.class, Long.parseLong(idField.getText().toString()));
                    finish();
                }
            }
        });
    }

    private void showFields(long id) {
        Doctor doctor = MainActivity.getDb().getEntityById(Doctor.class, id);

        EditText txtId = (EditText)findViewById(R.id.idField);
        EditText txtName = (EditText)findViewById(R.id.nameField);
        EditText txtSpecialization = (EditText)findViewById(R.id.specializatinoField);

        txtId.setText(Long.toString(id));
        txtName.setText(doctor.Name);
        txtSpecialization.setText(doctor.Specialization);
    }
}
