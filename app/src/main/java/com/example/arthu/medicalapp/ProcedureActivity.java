package com.example.arthu.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arthu.medicalapp.ApiService.ProcedureService;
import com.example.arthu.medicalapp.Entity.Procedure;

public class ProcedureActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure);

        showProcedures();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.showProcedures();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Cadastrar Procedimentos");
        menu.add(0, 1, 1, "Página inicial");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        switch(id) {
            case 0:
                intent= new Intent(this, ProcedureEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 1:
                intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 102);
                return true;
        }
        return false;
    }

    private void showProcedures() {
        final ListView procedureList = (ListView)findViewById(R.id.procedureView);

        Procedure[] values = new ProcedureService().getAll();

        ArrayAdapter<Procedure> studentAdapter =  new ArrayAdapter<Procedure>(this, android.R.layout.simple_list_item_1, values);

        procedureList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Procedure procedure = (Procedure)procedureList.getItemAtPosition(position);

                Intent i = new Intent(view.getContext(), ProcedureEntryActivity.class);
                i.putExtra("Id", procedure.getId());
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        procedureList.setAdapter(studentAdapter);
    }

}
