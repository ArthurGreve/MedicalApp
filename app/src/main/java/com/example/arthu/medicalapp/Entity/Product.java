package com.example.arthu.medicalapp.Entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Products")
public class Product extends Model {

    @Column(name = "Code", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String Code;
    @Column(name = "Name")
    public String Name;
    @Column(name = "Description")
    public String Description;

    public Product(){
        super();
    }

    public Product(String code, String name, String description){
        this();
        this.Code = code;
        this.Name = name;
        this.Description = description;
    }

    @Override
    public String toString() {
        return this.Code + " - " + this.Name;
    }
}
