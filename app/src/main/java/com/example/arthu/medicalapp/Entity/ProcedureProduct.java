package com.example.arthu.medicalapp.Entity;

public class ProcedureProduct {

    private int quantity;
    private Procedure procedure;
    private Product product;

    public ProcedureProduct(){
        this.quantity = 1;
    }

    public ProcedureProduct(Procedure procedure, Product product){
        this.quantity = 1;
        this.product = product;
        this.procedure = procedure;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
