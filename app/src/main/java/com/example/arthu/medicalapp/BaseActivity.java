package com.example.arthu.medicalapp;

import android.support.v7.app.AppCompatActivity;

import com.example.arthu.medicalapp.Entity.Database;

/**
 * Created by arthu on 05/06/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static final int REQUEST_CODE = 101;
    protected static Database db;
}
