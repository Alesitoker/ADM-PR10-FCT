package com.iessaladillo.alejandro.adm_pr10_fct.data;

import android.os.AsyncTask;

import com.iessaladillo.alejandro.adm_pr10_fct.base.Resource;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.CompanyDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.StudentDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.VisitDao;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RepositoryImpl implements Repository {

    private final CompanyDao companyDao;
    private final StudentDao studentDao;
    private final VisitDao visitDao;
    private MutableLiveData<Resource<Long>> insertResult = new MutableLiveData<>();
    private MutableLiveData<Resource<Integer>> result = new MutableLiveData<>();

    public RepositoryImpl(CompanyDao companyDao, StudentDao studentDao, VisitDao visitDao) {
        this.companyDao = companyDao;
        this.studentDao = studentDao;
        this.visitDao = visitDao;
    }

    @Override
    public LiveData<List<StudentCompany>> queryStudents() {
        return studentDao.queryStudents();
    }

    @Override
    public LiveData<StudentCompany> queryStudent(long studentId) {
        return studentDao.queryStudent(studentId);
    }

    @Override
    public LiveData<Resource<Long>> insertStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                long id = studentDao.insert(student);
                insertResult.postValue(Resource.success(id));
            } catch (Exception e) {
                insertResult.postValue(Resource.error(e));
            }
        });
        return insertResult;
    }

    @Override
    public LiveData<Resource<Integer>> updateStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int update = studentDao.update(student);
                result.postValue(Resource.success(update));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<Integer>> deleteStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int delete = studentDao.delete(student);
                result.postValue(Resource.success(delete));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<List<Company>> queryCompanies() {
        return companyDao.queryCompanies();
    }

    @Override
    public LiveData<Company> queryCompany(long companyId) {
        return companyDao.queryCompany(companyId);
    }

    @Override
    public LiveData<Resource<Long>> insertCompany(Company company) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                long id = companyDao.insert(company);
                insertResult.postValue(Resource.success(id));
            } catch (Exception e) {
                insertResult.postValue(Resource.error(e));
            }
        });
        return insertResult;
    }

    @Override
    public LiveData<Resource<Integer>> updateCompany(Company company) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int update = companyDao.update(company);
                result.postValue(Resource.success(update));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<Integer>> deleteCompany(Company company) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int delete = companyDao.delete(company);
                result.postValue(Resource.success(delete));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<List<VisitStudent>> queryVisits() {
        return visitDao.queryVisits();
    }

    @Override
    public LiveData<VisitStudent> queryVisit(long visitId) {
        return visitDao.queryVisit(visitId);
    }

    @Override
    public LiveData<Resource<Long>> insertVisit(Visit visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                long id = visitDao.insert(visit);
                insertResult.postValue(Resource.success(id));
            } catch (Exception e) {
                insertResult.postValue(Resource.error(e));
            }
        });
        return insertResult;
    }

    @Override
    public LiveData<Resource<Integer>> updateVisit(Visit visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int update = visitDao.update(visit);
                result.postValue(Resource.success(update));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<Integer>> deleteVisit(Visit visit) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                int delete = visitDao.delete(visit);
                result.postValue(Resource.success(delete));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }
}
