package com.example.arthu.medicalapp.ApiService;

import android.os.StrictMode;
import android.util.Log;

import com.example.arthu.medicalapp.Entity.Procedure;
import com.example.arthu.medicalapp.Entity.ProcedureProduct;
import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class ProcedureService extends ServiceBase<Procedure> {

    public ProcedureService(){
        super("procedures");
    }

    public void Update(ProcedureProduct pp){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(super.path).path("/update");

        Response response = webTarget.path("rest").request().get();
        response.readEntity(String.class);
        response.close();

        Gson gson = new Gson();
        String entityJson = gson.toJson(pp);

        Log.w(this.getClass().getName(), entityJson);
        webTarget.request().put(javax.ws.rs.client.Entity.json(entityJson));
    }

    @Override
    public <T> Procedure getById(T id) {
        String json = super.getById2(id);

        Procedure procedure;
        Gson gson = new Gson();
        procedure = gson.fromJson(json, Procedure.class);

        return procedure;
    }

    @Override
    public Procedure[] getAll() {
        String json  = super.getAll2();

        Procedure[] procedures;
        Gson gson = new Gson();
        procedures = gson.fromJson(json, Procedure[].class);

        return procedures;
    }
}
