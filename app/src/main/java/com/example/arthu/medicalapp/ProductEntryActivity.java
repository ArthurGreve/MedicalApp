package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arthu.medicalapp.ApiService.ProductService;
import com.example.arthu.medicalapp.Entity.Product;

public class ProductEntryActivity extends AppCompatActivity {

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);

        final Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnSave = (Button) findViewById(R.id.btnSave);

        if (this.getIntent().hasExtra("Code")) {
            this.code = this.getIntent().getStringExtra("Code");
            this.showFields(this.code);
        }else{
            btnDelete.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCode = (EditText) findViewById(R.id.codeField);
                EditText txtName = (EditText) findViewById(R.id.nameField);
                TextView descField = (TextView) findViewById(R.id.descField);

                if (!EntryHelper.isFieldsEmpty(txtCode, txtName, descField)) {
                    String code = txtCode.getText().toString();
                    String name = txtName.getText().toString();
                    String desc = descField.getText().toString();

                    Product product = new Product(code, name, desc);
                    ProductService api = new ProductService();

                    if(btnDelete.getVisibility() == View.INVISIBLE)
                        api.Add(product);
                    else
                        api.Update(product);

                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText codeField = (EditText) findViewById(R.id.codeField);

                if(!EntryHelper.isFieldsEmpty(codeField)){
                    ProductService api = new ProductService();
                    api.Delete(codeField.getText().toString());
                    finish();
                }
            }
        });
    }

    private void showFields(String code) {
        Product product = new ProductService().getById(code);

        EditText txtCode = (EditText)findViewById(R.id.codeField);
        EditText txtName = (EditText)findViewById(R.id.nameField);
        TextView descField = (TextView)findViewById(R.id.descField);

        txtCode.setEnabled(false);
        txtCode.setText(product.getCode());
        txtName.setText(product.getName());
        descField.setText(product.getDescription());
    }
}
