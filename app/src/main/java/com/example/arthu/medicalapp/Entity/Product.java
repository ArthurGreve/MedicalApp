package com.example.arthu.medicalapp.Entity;

public class Product {

    private String code;
    private String name;
    private String description;

    public Product(){}

    public Product(String code, String name, String description){
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
    @Override
    public String toString() {
        return this.code + " - " + this.name;
    }
}
