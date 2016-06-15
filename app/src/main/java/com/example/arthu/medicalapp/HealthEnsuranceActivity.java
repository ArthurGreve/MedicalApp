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
import com.example.arthu.medicalapp.Entity.HealthEnsurance;

import java.util.List;

public class HealthEnsuranceActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_ensurance);

        showHealthEnsurances();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);


        menu.add(0, 0, 0, "Cadastrar Planos de Saúde");
        menu.add(0, 1, 1, "Página inicial");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.showHealthEnsurances();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();


        switch(id) {
            case 0:
                intent = new Intent(this, HealthEnsuranceEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 1:
                intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    private void showHealthEnsurances() {
        final ListView heList = (ListView)findViewById(R.id.heView);

        List<HealthEnsurance> values = MainActivity.getDb().getList(HealthEnsurance.class);

        ArrayAdapter<HealthEnsurance> studentAdapter =  new ArrayAdapter<HealthEnsurance>(this, android.R.layout.simple_list_item_1, values);

        heList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HealthEnsurance healthEnsurance = (HealthEnsurance) heList.getItemAtPosition(position);

                Intent i = new Intent(view.getContext(), HealthEnsuranceEntryActivity.class);
                i.putExtra("Id", healthEnsurance.getId());
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        heList.setAdapter(studentAdapter);
    }
}
