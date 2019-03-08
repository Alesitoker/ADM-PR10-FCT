package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.base.EventObserver;
import com.iessaladillo.alejandro.adm_pr10_fct.base.TransferSelect;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Student;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentFormStudentBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.MainActivityViewModel;
import com.iessaladillo.alejandro.adm_pr10_fct.utils.KeyboardUtils;
import com.iessaladillo.alejandro.adm_pr10_fct.utils.ValidationUtils;

import java.util.Objects;

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
    private MainActivityViewModel activityViewModel;
    private long id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Objects.requireNonNull(getArguments());
        FormStudentFragmentArgs args = FormStudentFragmentArgs.fromBundle(getArguments());
        id = args.getId();
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
                Injector.provideRepository(requireContext()))).get(FormStudentFragmentViewModel.class);
        activityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        viewModel.setEditId(id);
        if (savedInstanceState == null && viewModel.getEditId() > 0) {
            viewModel.queryStudent().observe(this, student -> {
                stupForm(student);
                viewModel.setCompanyId(student.getCompanyId());
            });
        }
        setupViews();
        observe();
    }

    private void stupForm(StudentCompany student) {
        b.txtName.setText(student.getName());
        b.txtPhone.setText(String.valueOf(student.getPhone()));
        b.txtEmail.setText(student.getEmail());
        b.txtGrade.setText(student.getGrade());
        b.txtCompany.setText(student.getCompanyName());
        b.txtNameTutor.setText(student.getTutorName());
        b.txtPhoneTutor.setText(String.valueOf(student.getTutorPhone()));
        b.txtSchedule.setText(student.getSchedule());
    }

    private void observe() {
        viewModel.getSuccessMessage().observe(this, new EventObserver<>(message -> showMessage(message)));
        viewModel.getErrorMessage().observe(this, new EventObserver<>(message -> showMessage(message)));
        activityViewModel.getTransferred().observe(this, new EventObserver<>(tranferselec -> addCompany(tranferselec)));
    }

    private void addCompany(TransferSelect tranferselec) {
        KeyboardUtils.hideSoftKeyboard(requireActivity());
        viewModel.setCompanyId(tranferselec.getId());
        b.txtCompany.setText(tranferselec.getName());
    }

    private void showMessage(String message) {
        Toast.makeText(requireContext().getApplicationContext(), message, Toast.LENGTH_LONG).show();
        requireActivity().onBackPressed();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void setupViews() {
        b.txtCompany.setOnClickListener(v -> navigateToSelectCompany());
    }

    private void navigateToSelectCompany() {
        navController.navigate(R.id.actionFormStudentToSelectCompany);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_form_student, menu);
        if (viewModel.getEditId() <= 0) {
            menu.getItem(1).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        } else if (item.getItemId() == R.id.mnuDelete) {
            delete();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        Student student;

        closeKeyboard();
        if (checkFields()) {
            student = createStudent();

            if (viewModel.getEditId() > 0) {
                viewModel.updateStudent(student);
            } else {
                viewModel.insertStudent(student);
            }
        }
    }

    private void delete() {
        Student student = createStudent();

        closeKeyboard();
        viewModel.deleteStudent(student);
    }

    private Student createStudent() {
        String name, email, grade, tutorName, schedule;
        int phone, tutorPhone;
        long id = viewModel.getEditId() > 0 ? viewModel.getEditId(): 0;

        name = b.txtName.getText().toString();
        email = b.txtEmail.getText().toString();
        grade = b.txtGrade.getText().toString();
        tutorName = b.txtNameTutor.getText().toString();
        phone = Integer.parseInt(b.txtPhone.getText().toString());
        tutorPhone = Integer.parseInt(b.txtPhoneTutor.getText().toString());
        schedule = b.txtSchedule.getText().toString();

        return new Student(id, name, phone, email, grade, viewModel.getCompanyId(),
                tutorName, tutorPhone, schedule);
    }

    private void closeKeyboard() {
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private boolean checkFields() {
        boolean validName, validPhone, validEmail, validCompany, validTutorName,validTutorPhone;
        validName = checkName();
        validPhone = checkPhone();
        validEmail = checkEmail();
        validTutorName = checkTutorName();
        validTutorPhone = checkTutorPhone();
        validCompany = checkCompany();
        return validName && validPhone && validEmail && validCompany && validTutorName && validTutorPhone;
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
        if (!b.txtEmail.getText().toString().isEmpty() && ValidationUtils.isValidEmail(b.txtEmail.getText().toString())) {
            b.txtEmailLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtEmailLayout.setError("Email no valido");
            valid = false;
        }
        return valid;
    }

    private boolean checkTutorName() {
        boolean valid;
        if (!b.txtNameTutor.getText().toString().isEmpty()) {
            b.txtNameTutorLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtNameTutorLayout.setError("El campo nombre del tutor es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkTutorPhone() {
        boolean valid;
        if (!b.txtPhoneTutor.getText().toString().isEmpty() &&
                ValidationUtils.isValidPhone(b.txtPhoneTutor.getText().toString())) {
            b.txtPhoneTutorLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtPhoneTutorLayout.setError("El campo telefono del tutor es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkCompany() {
        boolean valid;
        if (!b.txtCompany.getText().toString().isEmpty()) {
            b.txtCompanyLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtCompanyLayout.setError("El campo empresa es requerido");
            valid = false;
        }
        return valid;
    }
}
