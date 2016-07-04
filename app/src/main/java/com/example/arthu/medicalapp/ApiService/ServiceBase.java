package com.example.arthu.medicalapp.ApiService;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public abstract class ServiceBase<Entity> {

    protected String path;

    protected ServiceBase(String path){
        //this.path = "http://192.168.1.7:8080/MedicalApi/rest/" + path;
        //this.path = "http://10.132.253.195:8080/MedicalApi/rest/" + path;
        this.path = "http://10.42.0.20:8080/MedicalApi/rest/" + path;
    }

    public void Add(Entity entity){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(this.path).path("/add");

        Response response = webTarget.path("rest").request().get();
        response.readEntity(String.class);
        response.close();

        Gson gson = new Gson();
        String entityJson = gson.toJson(entity);

        Log.w(this.getClass().getName(), entityJson);
        webTarget.request().post(javax.ws.rs.client.Entity.json(entityJson));
    }

    public void Update(Entity entity){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(this.path).path("/update");

        Response response = webTarget.path("rest").request().get();
        response.readEntity(String.class);
        response.close();

        Gson gson = new Gson();
        String entityJson = gson.toJson(entity);

        Log.w(this.getClass().getName(), entityJson);
        webTarget.request().put(javax.ws.rs.client.Entity.json(entityJson));
    }

    public <T> void Delete(T id){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(this.path);
        WebTarget webTargetExcluir = webTarget.path("{id}").resolveTemplate("id", id);

        webTargetExcluir.request().delete();
    }

    public abstract <T> Entity getById(T id);
    public abstract Entity[] getAll();

    protected <T> String getById2(T id){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(this.path).path("/" + id);
        String json = webTarget.request().get(String.class);
        Response response = webTarget.path("rest").request().get();
        response.readEntity(String.class);
        response.close();
        return json;
    }

    protected String getAll2(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(this.path).path("/list");
        String json = webTarget.request().get(String.class);
        Response response = webTarget.path("rest").request().get();
        response.readEntity(String.class);
        response.close();
        return json;
    }
}
