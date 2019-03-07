package com.iessaladillo.alejandro.adm_pr10_fct.data.local;

import com.iessaladillo.alejandro.adm_pr10_fct.base.BaseDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CompanyDao extends BaseDao<Company> {

    @Query("SELECT * FROM company WHERE id = :companyId")
    LiveData<Company> queryCompani(long companyId);

    @Query("SELECT * FROM company")
    LiveData<List<Company>> queryCompanies();
}
