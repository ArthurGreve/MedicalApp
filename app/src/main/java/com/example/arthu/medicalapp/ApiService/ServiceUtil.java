package com.example.arthu.medicalapp.ApiService;

public interface  ServiceUtil {
    public static final String IP = "192.168.1.8";
    public static final String PORT = "8080";
    public static final String SERVICE = "/MedicalApi/rest";
    public static final String PROTOCOL = "http";
    public static final String URL = PROTOCOL + "://" + IP + ":" + PORT + SERVICE;
}
