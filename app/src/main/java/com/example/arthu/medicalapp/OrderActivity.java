package com.example.arthu.medicalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Contacts;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.arthu.medicalapp.ApiService.OrderService;
import com.example.arthu.medicalapp.Entity.Order;

public class OrderActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    AlertDialog mAlertDialog;
    ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        showOrders();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Cadastrar Pedido");
        menu.add(0, 1, 1, "Página inicial");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.showOrders();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        switch(id) {
            case 0:
                intent= new Intent(this, OrderEntryActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            case 1:
                intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    private void showOrders() {
        orderList = (ListView)findViewById(R.id.orderList);

        Order[] values = new OrderService().getAll();

        final ArrayAdapter<Order> adapter =  new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, values);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Order order = (Order)orderList.getItemAtPosition(position);

                AlertDialog.Builder alert = new AlertDialog.Builder(OrderActivity.this);
                alert.setTitle("Delete entry");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        OrderService api = new OrderService();
                        api.Delete(order.getId());
                        showOrders();
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

        orderList.setAdapter(adapter);
    }
}
