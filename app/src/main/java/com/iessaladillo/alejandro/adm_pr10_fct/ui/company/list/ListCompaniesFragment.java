package com.iessaladillo.alejandro.adm_pr10_fct.ui.company.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Company;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentListCompaniesBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.ToolbarConfigurationInterface;
import com.iessaladillo.alejandro.adm_pr10_fct.utils.KeyboardUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

public class ListCompaniesFragment extends Fragment {

    private FragmentListCompaniesBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;
    private ListCompaniesFragmentViewModel viewModel;
    private ListCompaniesFragmentAdapter listAdapter;
    private NavController navController;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            toolbarConfiguration = (ToolbarConfigurationInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Listener must implement ToolbarConfigurationInterface");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentListCompaniesBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new ListCompaniesFragmentViewModelFactory(
                Injector.provideRepository(requireContext()))).get(ListCompaniesFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupViews();
        observeCompanies();
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void observeCompanies() {
        viewModel.getCompanies().observe(this, companies -> {
            listAdapter.submitList(companies);
            b.lblEmptyView.setVisibility(companies.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }

    private void setupViews() {
        setupRecyclerView();

        b.fabAdd.setOnClickListener(v -> navigateToAddCompany());
        b.lblEmptyView.setOnClickListener(v -> navigateToAddCompany());
    }

    private void setupRecyclerView() {
        listAdapter = new ListCompaniesFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(
                position -> NavigateToEditCompany(listAdapter.getItem(position)));

        b.lstCompany.setHasFixedSize(true);
        b.lstCompany.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstCompany_columns)));
        b.lstCompany.setAdapter(listAdapter);
    }

    private void NavigateToEditCompany(Company company) {
        ListCompaniesFragmentDirections.ActionCompaniesToFormCompany action =
                ListCompaniesFragmentDirections.actionCompaniesToFormCompany().setId(company.getId());
        navController.navigate(action);
    }

    private void navigateToAddCompany() {
        ListCompaniesFragmentDirections.ActionCompaniesToFormCompany action =
                ListCompaniesFragmentDirections.actionCompaniesToFormCompany().setId(0L);
        navController.navigate(action);
    }
}
