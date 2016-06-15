package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.arthu.medicalapp.Entity.HealthEnsurance;

public class HealthEnsuranceEntryActivity extends AppCompatActivity {

    private long Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_ensurance_entry);

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

                if (!EntryHelper.isFieldsEmpty(txtName)) {
                    HealthEnsurance healthEnsurance;
                    String name = txtName.getText().toString();
                    if (TextUtils.isEmpty(txtId.getText())) {
                        healthEnsurance = new HealthEnsurance(name);
                    }else {
                        healthEnsurance = MainActivity.getDb().getEntityById(HealthEnsurance.class, Long.parseLong(txtId.getText().toString()));
                        healthEnsurance.Name = name;
                    }
                    MainActivity.getDb().save(healthEnsurance);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText idField = (EditText) findViewById(R.id.idField);

                if(!EntryHelper.isFieldsEmpty(idField)){
                    MainActivity.getDb().delete(HealthEnsurance.class, Long.parseLong(idField.getText().toString()));
                    finish();
                }
            }
        });
    }

    private void showFields(long id) {
        HealthEnsurance healthEnsurance = MainActivity.getDb().getEntityById(HealthEnsurance.class, id);

        EditText txtId = (EditText)findViewById(R.id.idField);
        EditText txtName = (EditText)findViewById(R.id.nameField);

        txtId.setText(Long.toString(id));
        txtName.setText(healthEnsurance.Name);
    }
}
