package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.form.selectCompany;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentListCompaniesBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.company.form.FormCompanyFragmentViewModel;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.company.form.FormCompanyFragmentViewModelFactory;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.company.list.ListCompaniesFragmentAdapter;

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

public class SelectCompanyFragment extends Fragment {

    private FragmentListCompaniesBinding b;
    private NavController navController;
    private ListCompaniesFragmentAdapter listAdapter;
    private SelectCompanyFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentListCompaniesBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new FormCompanyFragmentViewModelFactory(
                Injector.provideRepository(requireContext()))).get(SelectCompanyFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupRecyclerView();
        observeCompanies();
    }

    private void observeCompanies() {
        viewModel.getCompanies().observe(this, companies -> {
            listAdapter.submitList(companies);
            b.lblEmptyView.setVisibility(companies.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupRecyclerView() {
        listAdapter = new ListCompaniesFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(
                position -> SendCompany(listAdapter.getItem(position)));

        b.lstCompany.setHasFixedSize(true);
        b.lstCompany.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstCompany_columns)));
        b.lstCompany.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        b.lstCompany.setAdapter(listAdapter);
    }

    private void SendCompany(Company company) {

    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }
}
