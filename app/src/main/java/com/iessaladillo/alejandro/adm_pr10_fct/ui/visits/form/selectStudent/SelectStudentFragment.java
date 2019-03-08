package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form.selectStudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.base.TransferSelect;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentListStudentsBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentSelectStudentBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.MainActivityViewModel;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form.selectCompany.SelectCompanyFragmentViewModel;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list.ListStudentsFragmentAdapter;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

public class SelectStudentFragment extends Fragment {

    private FragmentSelectStudentBinding b;
    private NavController navController;
    private SelectStudentFragmentAdapter listAdapter;
    private SelectStudentFragmentViewModel viewModel;
    private MainActivityViewModel activityViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentSelectStudentBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new SelectStudentFragmentViewModelFactory(
                Injector.provideRepository(requireContext().getApplicationContext()))).get(SelectStudentFragmentViewModel.class);
        activityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupRecyclerView();
        observeVisits();
    }

    private void observeVisits() {
        viewModel.getVisits().observe(this, visits -> {
            listAdapter.submitList(visits);
            if (visits.size() == 0) {
                salir();
            }
        });
    }

    private void salir() {
        requireActivity().onBackPressed();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        listAdapter = new SelectStudentFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(position -> sendStudent(listAdapter.getItem(position)));

        b.lstStudent.setHasFixedSize(true);
        b.lstStudent.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstStudent_columns)));
        b.lstStudent.setAdapter(listAdapter);
    }

    private void sendStudent(StudentCompany student) {
        activityViewModel.setTransferred(new TransferSelect(student.getId(), student.getName()));
        salir();
    }
}
