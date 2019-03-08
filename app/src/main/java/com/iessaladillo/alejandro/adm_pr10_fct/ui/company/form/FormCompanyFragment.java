package com.iessaladillo.alejandro.adm_pr10_fct.ui.company.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.base.EventObserver;
import com.iessaladillo.alejandro.adm_pr10_fct.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.AppDatabase;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentFormCompanyBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
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

public class FormCompanyFragment extends Fragment {

    private FragmentFormCompanyBinding b;
    private FormCompanyFragmentViewModel viewModel;
    private NavController navController;
    private long id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Objects.requireNonNull(getArguments());
        FormCompanyFragmentArgs args = FormCompanyFragmentArgs.fromBundle(getArguments());
        id = args.getId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentFormCompanyBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new FormCompanyFragmentViewModelFactory(
                Injector.provideRepository(requireContext()))).get(FormCompanyFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        if (id > 0) {
            viewModel.queryCompany(id).observe(this, company -> setupForm(company));
        }
        setupViews();
        observe();
    }

    private void setupForm(Company company) {
        b.txtName.setText(company.getName());
        b.txtCIF.setText(company.getCif());
        b.txtAddress.setText(company.getAddress());
        b.txtPhone.setText(company.getPhone());
        b.txtEmail.setText(company.getEmail());
        b.txtLogo.setText(company.getLogo());
        b.txtContactName.setText(company.getContactName());
    }

    private void observe() {
        viewModel.getSuccessMessage().observe(this, new EventObserver<>(message -> showMessage()));
        viewModel.getErrorMessage().observe(this, new EventObserver<>(message -> showMessage()));
    }

    private void showMessage() {
        // TODO: Preguntar cual es mejor.
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
        inflater.inflate(R.menu.fragment_form_company, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // TODO: crear un objeto dependiendo de si voy a actualizar o insertar
    private void save() {
        String name, cif, address, email, logo, contactName;
        int id = 0, phone;

        if (checkFields()) {
            name = b.txtName.getText().toString();
            cif = b.txtCIF.getText().toString();
            address = b.txtAddress.getText().toString();
            phone = Integer.parseInt(b.txtPhone.getText().toString());
            email = b.txtEmail.getText().toString();
            logo = b.txtLogo.getText().toString();
            contactName = b.txtContactName.getText().toString();

//            viewModel.insertCompany(new Company(id, name, cif, address, phone, email, logo, contactName));
        }
    }

    private boolean checkFields() {
        boolean validName, validCIF, validAddress, validPhone, validEmail, validLogo;
        validName = checkName();
        validCIF = checkCIF();
        validAddress = checkAddress();
        validPhone = checkPhone();
        validEmail = checkEmail();
        validLogo = checkUrlLogo();
        return validName && validCIF && validAddress && validPhone && validEmail && validLogo;
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

    private boolean checkCIF() {
        boolean valid;
        if (!b.txtCIF.getText().toString().isEmpty()) {
            b.txtCIFLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtCIFLayout.setError("El campo CIF es requerido");
            valid = false;
        }
        return valid;
    }

    private boolean checkAddress() {
        boolean valid;
        if (!b.txtAddress.getText().toString().isEmpty()) {
            b.txtAddressLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtAddressLayout.setError("El campo direccion es requerido");
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

    private boolean checkUrlLogo() {
        boolean valid;
        if (ValidationUtils.isValidUrl(b.txtLogo.getText().toString())) {
            b.txtLogoLayout.setErrorEnabled(false);
            valid = true;
        } else {
            b.txtLogoLayout.setError("La url no es valida");
            valid = false;
        }
        return valid;
    }

}
