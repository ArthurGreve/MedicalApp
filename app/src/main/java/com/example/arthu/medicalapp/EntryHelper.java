package com.example.arthu.medicalapp;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by arthu on 05/06/2016.
 */
public final class EntryHelper {

    public static boolean isFieldsEmpty(TextView... fields) {
        boolean empty = false;

        for (TextView field: fields)
        {
            if(TextUtils.isEmpty(field.getText().toString())) {
                empty = true;
                field.setError("Campo deve ser preenchido.");
            }
        }

        return empty;
    }

    public static <T> T getValue(T typeSource, TextView field){
        /*if(typeSource instanceof Integer){
            return (T)field.getText().toString();
        } else if(typeSource instanceof String){
            return (T)field.getText().toString();
        }*/
        return (T)field.getText().toString();
    }
}

