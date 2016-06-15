package com.example.arthu.medicalapp.Entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="HealthEnsurances")
public class HealthEnsurance extends Model {
    /*@Column(name = "Id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long Id;*/
    @Column(name = "Name")
    public String Name;

    public HealthEnsurance(){
        super();
    }

    public HealthEnsurance(String name){
        this();
        this.Name = name;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
