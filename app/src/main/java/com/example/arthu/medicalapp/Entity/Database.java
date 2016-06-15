package com.example.arthu.medicalapp.Entity;

import android.support.v7.app.AppCompatActivity;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.List;

public class Database {

    private final AppCompatActivity context;

    public Database(AppCompatActivity context) {
        this.context = context;
        ActiveAndroid.initialize(this.context);
    }

    public <T extends Model> T getEntityById(Class<T> type, String clause){
        return (T) new Select().from(type).where(clause).executeSingle();
    }

    public <T extends Model> T getEntityById(Class<T> type, long id){
        return (T)Model.load(type, id);
    }

    public void save(Model entity){
        entity.save();
    }

    public <T extends Model> void delete(Class<T> type, long id){
        T entity = Model.load(type, id);
        entity.delete();
    }

    public <T extends Model> void delete(Class<T> type, String clause){
        T entity = getEntityById(type, clause);
        entity.delete();
    }

    public <T extends Model> List<T> getList(Class<T> type){
        return new Select()
                .from(type)
                .orderBy("Name ASC")
                .execute();
    }
}
