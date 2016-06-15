package com.example.arthu.medicalapp.Entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name="ProcedureProducts")
public class ProcedureProduct extends Model{
    @Column(name="ProcedureId", onDelete = Column.ForeignKeyAction.CASCADE)
    public Procedure Procedure;
    @Column(name = "ProductId", onDelete = Column.ForeignKeyAction.CASCADE)
    public Product Product;

    @Column(name="Quantity")
    public int Quantity;

    public ProcedureProduct() { super(); }

    public ProcedureProduct(Procedure procedure, Product product){
        this();
        this.Procedure = procedure;
        this.Product = product;
        this.Quantity = 1;
    }

    public List<Procedure> Procedures(){
        return getMany(Procedure.class, "ProcedureProduct");
    }

    public List<Product> Products(){
        return getMany(Product.class, "ProcedureProduct");
    }
}
