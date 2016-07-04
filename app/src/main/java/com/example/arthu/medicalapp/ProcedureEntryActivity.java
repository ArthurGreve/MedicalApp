package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arthu.medicalapp.Adapters.ProductCheckBoxAdapter;
import com.example.arthu.medicalapp.ApiService.ProcedureService;
import com.example.arthu.medicalapp.ApiService.ProductService;
import com.example.arthu.medicalapp.Entity.Procedure;
import com.example.arthu.medicalapp.Entity.ProcedureProduct;
import com.example.arthu.medicalapp.Entity.Product;
import com.example.arthu.medicalapp.Entity.ProductQuantity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProcedureEntryActivity extends AppCompatActivity {

    private long Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_entry);

        List<ProductQuantity> products = getProducts();

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        Button btnSave = (Button) findViewById(R.id.btnSave);

        if (this.getIntent().hasExtra("Id")) {
            this.Id = this.getIntent().getLongExtra("Id", -1);
            this.showFields(this.Id, products);
        } else {
            btnDelete.setVisibility(View.INVISIBLE);
        }

        final ListView lv = (ListView) findViewById(R.id.productsList);
        final ProductCheckBoxAdapter adapter = new ProductCheckBoxAdapter(this, products);
        lv.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtId = (EditText) findViewById(R.id.idField);
                EditText txtName = (EditText) findViewById(R.id.nameField);

                Map<Product, Boolean> products = adapter.getItemChecked();

                if (!EntryHelper.isFieldsEmpty(txtName) && products.size() != 0) {
                    Procedure procedure;
                    ProcedureProduct pp;
                    String name = txtName.getText().toString();

                    ProcedureService api = new ProcedureService();

                    if (TextUtils.isEmpty(txtId.getText())) {
                        procedure = new Procedure(name);

                        for (Map.Entry<Product, Boolean> prod : products.entrySet()) {
                            procedure.addProduct(new ProductQuantity(prod.getKey(), 1));
                        }
                        api.Add(procedure);
                    } else {
                        //procedure = MainActivity.getDb().getEntityById(Procedure.class, Long.parseLong(txtId.getText().toString()));
                        //healthEnsurance.Name = name;
                    }
                    finish();
                }
            }
        });
    }

    private List<ProductQuantity> getProducts() {
        Product[] products = new ProductService().getAll();
        List<ProductQuantity> productQuantities = new ArrayList<>();

        for (Product product: products){
            productQuantities.add(new ProductQuantity(product, 0));
        }

        return productQuantities;
    }

    private void showFields(Long id, List<ProductQuantity> products){
        Procedure procedure = new ProcedureService().getById(id);

        EditText txtId = (EditText)findViewById(R.id.idField);
        EditText txtName = (EditText)findViewById(R.id.nameField);

        txtId.setEnabled(false);
        txtId.setText(procedure.getId().toString());
        txtName.setText(procedure.getName());

        for(ProductQuantity p: procedure.getProducts()){
            for (ProductQuantity p2: products){
                if(p2.getCode().equals(p.getCode())){
                    p2.setQuantity(p.getQuantity());
                }
            }
        }
    }
}
