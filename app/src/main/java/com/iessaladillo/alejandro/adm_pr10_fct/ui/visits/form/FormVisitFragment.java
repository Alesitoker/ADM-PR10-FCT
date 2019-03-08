package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.base.DatePickerDialogFragment;
import com.iessaladillo.alejandro.adm_pr10_fct.base.EventObserver;
import com.iessaladillo.alejandro.adm_pr10_fct.base.TimePickerDialogFragment;
import com.iessaladillo.alejandro.adm_pr10_fct.base.TransferSelect;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentFormVisitBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.MainActivityViewModel;
import com.iessaladillo.alejandro.adm_pr10_fct.utils.KeyboardUtils;

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

public class FormVisitFragment extends Fragment implements OnDateSetListener, OnTimeSetListener {

    private FragmentFormVisitBinding b;
    private FormVisitFragmentViewModel viewModel;
    private NavController navController;
    private MainActivityViewModel activityViewModel;
    private long id;
    private final int DATE_REQUESTCODE = 1, TIME_REQUESTCODE = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Objects.requireNonNull(getArguments());
        FormVisitFragmentArgs args = FormVisitFragmentArgs.fromBundle(getArguments());
        id = args.getId();
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
        activityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        viewModel.setEditId(id);
        if (savedInstanceState == null && viewModel.getEditId() > 0) {
            viewModel.queryVisit().observe(this, visit -> {
                stupForm(visit);
                viewModel.setStudentId(visit.getStudentId());
            });
        }
        setupToolbar();
        setupViews();
        observe();
    }

    private void stupForm(VisitStudent visit) {
        b.txtDay.setText(visit.getDay());
        b.txtStartTime.setText(visit.getStartTime());
        b.txtEndTime.setText(visit.getEndTime());
        b.txtStudent.setText(visit.getStudentName());
        b.txtObservations.setText(visit.getObservations());
    }

    private void observe() {
        viewModel.getSuccessMessage().observe(this, new EventObserver<>(message -> showMessage(message)));
        viewModel.getErrorMessage().observe(this, new EventObserver<>(message -> showMessage(message)));
        activityViewModel.getTransferred().observe(this, new EventObserver<>(tranferselec -> addStudent(tranferselec)));
    }

    private void addStudent(TransferSelect tranferselec) {
        viewModel.setStudentId(tranferselec.getId());
        b.txtStudent.setText(tranferselec.getName());
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
        b.txtStudent.setOnClickListener(v -> navigateToSelectStudent());
        b.txtDay.setOnClickListener(v -> openDialogDate());
        b.txtStartTime.setOnClickListener(v -> openDialogTime("s"));
        b.txtEndTime.setOnClickListener(v -> openDialogTime("e"));
    }

    private void openDialogDate() {
        DatePickerDialogFragment.newInstance(this, DATE_REQUESTCODE).show(requireFragmentManager(),
                "DatePickerDialogFragment");
    }

    private void openDialogTime(String value) {
        viewModel.setWhatTime(value);
        TimePickerDialogFragment.newInstance(this, TIME_REQUESTCODE).show(requireFragmentManager(),
                "TimePickerDialogFragment");
    }

    private void navigateToSelectStudent() {
        navController.navigate(R.id.actionFormVisitToSelectStudent);
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
        Visit visit;

        closeKeyboard();
        if (checkFields()) {
            visit = createVisit();

            if (viewModel.getEditId() > 0) {
                viewModel.updateVisit(visit);
            } else {
                viewModel.insertVisit(visit);
            }

        }
    }

    private Visit createVisit() {
        String day, startTime, endTime, observations;
        long id = viewModel.getEditId() > 0 ? viewModel.getEditId(): 0;

        day = b.txtDay.getText().toString();
        startTime = b.txtStartTime.getText().toString();
        endTime = b.txtEndTime.getText().toString();
        observations = b.txtObservations.getText().toString();

        return new Visit(id, day, startTime, endTime, observations, viewModel.getStudentId());
    }

    private void closeKeyboard() {
        KeyboardUtils.hideSoftKeyboard(requireActivity());
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
            b.txtStartTimeLayout.setError("El campo hora de inicio es requerido");
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
            b.txtEndTimeLayout.setError("El campo hora de fin es requerido");
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
            b.txtStudentLayout.setError("El campo alumno es requerido");
            valid = false;
        }
        return valid;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        b.txtDay.setText(getString(R.string.txtDay_value, dayOfMonth, month, year));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (viewModel.getWhatTime().equals("s")) {
            b.txtStartTime.setText(getString(R.string.txtStartTime_value, hourOfDay, minute));
            if (b.txtEndTime.getText().toString().isEmpty()) {
                b.txtEndTime.setText(getString(R.string.txtEndTime_value, hourOfDay + 1, minute));
            }
        } else {
            b.txtEndTime.setText(getString(R.string.txtEndTime_value, hourOfDay, minute));
        }
    }
}
