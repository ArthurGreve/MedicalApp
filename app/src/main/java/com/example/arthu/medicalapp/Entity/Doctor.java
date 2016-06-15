package com.example.arthu.medicalapp.Entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="Doctors")
public class Doctor extends Model {

    @Column(name="Name")
    public String Name;
    @Column(name="Specialization")
    public String Specialization;

    public Doctor(){
        super();
    }

    public Doctor(String name, String specialization){
        this();
        this.Name = name;
        this.Specialization = specialization;
    }

    @Override
    public String toString() {
        return this.Name + " - " + this.Specialization;
    }
}
