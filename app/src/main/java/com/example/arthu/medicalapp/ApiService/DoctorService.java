package com.example.arthu.medicalapp.ApiService;

import com.example.arthu.medicalapp.Entity.Doctor;
import com.google.gson.Gson;

public class DoctorService extends ServiceBase<Doctor> {

    public DoctorService(){
        super("doctors");
    }

    @Override
    public <T> Doctor getById(T id) {
        String json = super.getById2(id);

        Doctor doctor;
        Gson gson = new Gson();
        doctor = gson.fromJson(json, Doctor.class);

        return doctor;
    }

    @Override
    public Doctor[] getAll() {
        String json  = super.getAll2();

        Doctor[] doctors;
        Gson gson = new Gson();
        doctors = gson.fromJson(json, Doctor[].class);

        return doctors;
    }
}
