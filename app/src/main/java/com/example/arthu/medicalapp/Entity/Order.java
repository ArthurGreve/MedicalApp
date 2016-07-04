package com.example.arthu.medicalapp.Entity;

public class Order {
    private Long id;
    private Procedure procedure;
    private Doctor doctor;
    private HealthEnsurance healthEnsurance;

    public Order(){}

    public Order(Procedure procedure, Doctor doctor, HealthEnsurance healthEnsurance){
        this.procedure = procedure;
        this.doctor = doctor;
        this.healthEnsurance = healthEnsurance;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setProcedure(Procedure procedure){
        this.procedure = procedure;
    }

    public Procedure getProcedure(){
        return procedure;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public void setHealthEnsurance(HealthEnsurance healthEnsurance){
        this.healthEnsurance = healthEnsurance;
    }

    public HealthEnsurance getHealthEnsurance(){
        return healthEnsurance;
    }

    @Override
    public String toString() {
        return getProcedure().getName() + " - " + getDoctor().getName() + " - " + getHealthEnsurance().getName();
    }
}
