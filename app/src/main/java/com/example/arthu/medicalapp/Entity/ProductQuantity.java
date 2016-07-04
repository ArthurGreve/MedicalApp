package com.example.arthu.medicalapp.Entity;

public class ProductQuantity extends Product {

    private int quantity;

    public ProductQuantity(String code, String name, String description, int quantity){
        this.setCode(code);
        this.setName(name);
        this.setDescription(description);
        this.setQuantity(quantity);
    }

    public ProductQuantity(Product p, int quantity){
        this(p.getCode(), p.getName(), p.getDescription(), quantity);
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }


}
