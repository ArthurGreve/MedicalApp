package com.example.arthu.medicalapp.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Procedure {
    private Long id;
    private String name;
    private List<ProductQuantity> products;

    public Procedure(){
        this.products = new ArrayList<>();
    }

    public Procedure(String name){
        this.name = name;
        this.products= new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(ProductQuantity p){
        this.products.add(p);
    }

    public List<ProductQuantity> getProducts(){
        return products;
    }
    @Override
    public String toString() {
        return this.getName().toUpperCase();
    }
}
