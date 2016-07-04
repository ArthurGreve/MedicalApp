package com.example.arthu.medicalapp.Entity;

public class HealthEnsurance {
    private Long id;
    private String name;

    public HealthEnsurance(){    }

    public HealthEnsurance(String name){
        this.name = name;
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
    @Override
    public String toString() {
        return this.getName();
    }
}
