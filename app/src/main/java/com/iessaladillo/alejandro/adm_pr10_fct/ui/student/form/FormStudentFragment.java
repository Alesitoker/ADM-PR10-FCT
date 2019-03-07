package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.base.EventObserver;
import com.iessaladillo.alejandro.adm_pr10_fct.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentFormStudentBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.utils.ValidationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class FormStudentFragment extends Fragment {

    private FragmentFormStudentBinding b;
    private FormStudentFragmentViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentFormStudentBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new FormStudentFragmentViewModelFactory(
                new RepositoryImpl())).get(FormStudentFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupViews();
        observe();
    }

    private void observe() {
        viewModel.getSuccessMessage().observe(this, new EventObserver<>(message -> showMessage()));
        viewModel.getErrorMessage().observe(this, new EventObserver<>(message -> showMessage()));
    }

    private void showMessage() {
        navController.navigateUp();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void setupViews() {

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_form_student, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {

        if (checkFields()) {

        }
    }

    private boolean checkFields() {
        boolean validName, validPhone, validEmail, validTutorName, validTutorPhone;
        validName = checkName();
        validPhone = checkPhone();
        validEmail = checkEmail();
        validTutorName = checkTutorName();
        validTutorPhone = checkTutorPhone();
        return validName && validPhone && validEmail && validTutorName && validTutorPhone;
    }

    private boolean checkName() {
        boolean valid;
        if (!b.txtName.getText().toString().isEmpty()) {
            b.txtNameLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtNameLayout.setError("El campo nombre es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkPhone() {
        boolean valid;
        if (!b.txtPhone.getText().toString().isEmpty() &&
                ValidationUtils.isValidPhone(b.txtPhone.getText().toString())) {
            b.txtPhoneLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtPhoneLayout.setError("El campo telefono es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkEmail() {
        boolean valid;
        if (ValidationUtils.isValidEmail(b.txtEmail.getText().toString())) {
            b.txtEmailLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtEmailLayout.setError("El email no es valido");
            valid = true;
        }
        return valid;
    }

    private boolean checkTutorName() {
        boolean valid;
        if (ValidationUtils.isValidUrl(b.txtNameTutor.getText().toString())) {
            b.txtNameTutorLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtNameTutorLayout.setError("La url no es valida");
            valid = false;
        }
        return valid;
    }

    private boolean checkTutorPhone() {
        boolean valid;
        if (ValidationUtils.isValidUrl(b.txtPhoneTutor.getText().toString()) &&
                ValidationUtils.isValidPhone(b.txtPhone.getText().toString())) {
            b.txtPhoneTutorLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtPhoneTutorLayout.setError("La url no es valida");
            valid = false;
        }
        return valid;
    }

    private boolean checkCompany() {
        boolean valid;
        if (ValidationUtils.isValidUrl(b.txtCompany.getText().toString())) {
            b.txtCompanyLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtCompanyLayout.setError("La url no es valida");
            valid = false;
        }
        return valid;
    }
}
