package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentFormStudentBinding;

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
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void setupViews() {

    }
}
