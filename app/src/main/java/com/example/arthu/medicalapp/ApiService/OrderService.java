package com.example.arthu.medicalapp.ApiService;

import com.example.arthu.medicalapp.Entity.Order;
import com.google.gson.Gson;

public class OrderService extends ServiceBase<Order> {

    public OrderService(){
        super("orders");
    }

    @Override
    public <T> Order getById(T id) {
        String json = super.getById2(id);

        Order order;
        Gson gson = new Gson();
        order = gson.fromJson(json, Order.class);

        return order;
    }

    @Override
    public Order[] getAll() {
        String json  = super.getAll2();

        Order[] orders;
        Gson gson = new Gson();
        orders = gson.fromJson(json, Order[].class);

        return orders;
    }
}
