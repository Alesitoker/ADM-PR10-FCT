package com.iessaladillo.alejandro.adm_pr10_fct.di;

import android.content.Context;

import com.iessaladillo.alejandro.adm_pr10_fct.data.Repository;
import com.iessaladillo.alejandro.adm_pr10_fct.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.AppDatabase;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.CompanyDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.StudentDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.VisitDao;

public class Injector {

    private Injector() {}

    public static Repository provideRepository(Context context) {
        return new RepositoryImpl(provideCompanyDao(context), provideStudentDao(context), provideVisitDao(context));
    }

    private static CompanyDao provideCompanyDao(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext()).companyDao();
    }

    private static StudentDao provideStudentDao(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext()).studentDao();
    }

    private static VisitDao provideVisitDao(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext()).visitDao();
    }
}
