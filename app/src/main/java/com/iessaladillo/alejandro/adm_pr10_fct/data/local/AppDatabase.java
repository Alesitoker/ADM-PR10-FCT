package com.iessaladillo.alejandro.adm_pr10_fct.data.local;

import android.content.Context;

import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {Student.class, Company.class, Visit.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
 
    private static final String DATABASE_NAME = "fct-database";
 
    public abstract StudentDao studentDao();
    public abstract CompanyDao companyDao();
    public abstract VisitDao visitDao();
 
    private static volatile AppDatabase instance;
 
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = 
                        Room.databaseBuilder(
                            context.getApplicationContext(), AppDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
 
}