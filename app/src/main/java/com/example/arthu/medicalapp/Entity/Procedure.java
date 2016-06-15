package com.example.arthu.medicalapp.Entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name="Procedures")
public class Procedure extends Model{

    @Column(name = "Name")
    public String Name;

    public Procedure(){super();}

    public Procedure(String name){
        this();
        this.Name = name;
    }

    @Override
    public String toString() {
        return this.Name.toUpperCase();
    }
}
