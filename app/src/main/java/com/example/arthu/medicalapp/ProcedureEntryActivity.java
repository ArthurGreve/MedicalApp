package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.arthu.medicalapp.Adapters.ProductCheckBoxAdapter;
import com.example.arthu.medicalapp.Entity.Procedure;
import com.example.arthu.medicalapp.Entity.ProcedureProduct;
import com.example.arthu.medicalapp.Entity.Product;

import java.util.List;
import java.util.Map;

public class ProcedureEntryActivity extends AppCompatActivity {

    private long Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_entry);

        List<Product> products = getProducts();
        final ListView lv = (ListView) findViewById(R.id.productsList);
        //lv.setItemsCanFocus(true);
        final ProductCheckBoxAdapter adapter = new ProductCheckBoxAdapter(this, products);
        lv.setAdapter(adapter);

        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnSave2 = (Button)findViewById(R.id.btnSave2);

        if (this.getIntent().hasExtra("Id")) {
            this.Id = this.getIntent().getLongExtra("Id", -1);
            //this.showFields(this.Id);
        }else{
            btnDelete.setVisibility(View.INVISIBLE);
        }

            btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtId = (EditText)findViewById(R.id.idField);
                EditText txtName = (EditText)findViewById(R.id.nameField);

                Map<Product, Boolean> products = adapter.getItemChecked();

                if (!EntryHelper.isFieldsEmpty(txtName) && products.size() != 0) {
                    Procedure procedure;
                    ProcedureProduct pp;
                    String name = txtName.getText().toString();

                    if (TextUtils.isEmpty(txtId.getText())) {
                        procedure = new Procedure(name);

                        for (Map.Entry<Product, Boolean> prod: products.entrySet()) {
                            pp = new ProcedureProduct(procedure, prod.getKey());
                            MainActivity.getDb().save(pp);
                        }
                    } else {
                        procedure = MainActivity.getDb().getEntityById(Procedure.class, Long.parseLong(txtId.getText().toString()));
                        //healthEnsurance.Name = name;
                    }
                    finish();
                }
            }
        });
    }

    private List<Product> getProducts(){
        return MainActivity.getDb().getList(Product.class);
    }
}
