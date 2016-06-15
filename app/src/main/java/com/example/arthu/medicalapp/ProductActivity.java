package com.example.arthu.medicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arthu.medicalapp.Entity.Database;
import com.example.arthu.medicalapp.Entity.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        showProducts();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Cadastrar Produto");
        menu.add(0, 1, 1, "Página inicial");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.showProducts();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        switch(id) {
            case 0:
                intent= new Intent(this, ProductEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 1:
                intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    private void showProducts() {
        final ListView productList = (ListView)findViewById(R.id.productView);

        List<Product> values = MainActivity.getDb().getList(Product.class);

        ArrayAdapter<Product> studentAdapter =  new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, values);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product)productList.getItemAtPosition(position);

                Intent i = new Intent(view.getContext(), ProductEntryActivity.class);
                i.putExtra("Code", product.Code);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        productList.setAdapter(studentAdapter);
    }

}
