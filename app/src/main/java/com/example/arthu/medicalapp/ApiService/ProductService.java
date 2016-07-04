package com.example.arthu.medicalapp.ApiService;

import com.example.arthu.medicalapp.Entity.Product;
import com.google.gson.Gson;

public class ProductService extends ServiceBase<Product> {

    public ProductService(){
        super("products");
    }

    @Override
    public <T> Product getById(T id) {
        String json = super.getById2(id);

        Product product;
        Gson gson = new Gson();
        product = gson.fromJson(json, Product.class);

        return product;
    }

    @Override
    public Product[] getAll() {
        String json  = super.getAll2();

        Product[] products;
        Gson gson = new Gson();
        products = gson.fromJson(json, Product[].class);

        return products;
    }
}
