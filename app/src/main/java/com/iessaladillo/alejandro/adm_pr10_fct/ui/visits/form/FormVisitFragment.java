package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.base.EventObserver;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentFormVisitBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;

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

public class FormVisitFragment extends Fragment {

    private FragmentFormVisitBinding b;
    private FormVisitFragmentViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentFormVisitBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this , new FormVisitFragmentViewModelFactory(
                Injector.provideRepository(requireContext()))).get(FormVisitFragmentViewModel.class);
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
        inflater.inflate(R.menu.fragment_form_visit, menu);
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
        boolean validName, validStartTime, validEndTime, validStudent;
        validName = checkName();
        validStartTime = checkStartTime();
        validEndTime = checkEndTime();
        validStudent = checkStudent();
        return validName && validStartTime && validEndTime && validStudent;
    }

    private boolean checkName() {
        boolean valid;
        if (!b.txtDay.getText().toString().isEmpty()) {
            b.txtDayLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtDayLayout.setError("El campo nombre es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkStartTime() {
        boolean valid;
        if (!b.txtStartTime.getText().toString().isEmpty()) {
            b.txtStartTimeLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtStartTimeLayout.setError("El campo telefono es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkEndTime() {
        boolean valid;
        if (!b.txtEndTime.getText().toString().isEmpty()) {
            b.txtEndTimeLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtEndTimeLayout.setError("El campo telefono es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkStudent() {
        boolean valid;
        if (!b.txtStudent.getText().toString().isEmpty()) {
            b.txtStudentLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtStudentLayout.setError("El campo telefono es requerido");
            valid = false;
        }
        return valid;
    }

}
