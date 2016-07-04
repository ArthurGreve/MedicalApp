package com.example.arthu.medicalapp.ApiService;

import com.example.arthu.medicalapp.Entity.HealthEnsurance;
import com.google.gson.Gson;

public class HealthEnsuranceService extends ServiceBase<HealthEnsurance> {

    public HealthEnsuranceService(){
        super("healthensurances");
    }

    @Override
    public <T> HealthEnsurance getById(T id) {
        String json = super.getById2(id);

        HealthEnsurance healthEnsurance;
        Gson gson = new Gson();
        healthEnsurance = gson.fromJson(json, HealthEnsurance.class);

        return healthEnsurance;
    }

    @Override
    public HealthEnsurance[] getAll() {
        String json  = super.getAll2();

        HealthEnsurance[] healthEnsurances;
        Gson gson = new Gson();
        healthEnsurances = gson.fromJson(json, HealthEnsurance[].class);

        return healthEnsurances;
    }
}
