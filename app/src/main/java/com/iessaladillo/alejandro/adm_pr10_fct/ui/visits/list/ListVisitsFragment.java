package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.Visit;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentListVisitsBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.ToolbarConfigurationInterface;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

public class ListVisitsFragment extends Fragment {

    private FragmentListVisitsBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;
    private ListVisitsFragmentViewModel viewModel;
    private ListVisitsFragmentAdapter listAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarConfiguration = (ToolbarConfigurationInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentListVisitsBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this,
                new ListVisitsFragmentViewModelFactory(new RepositoryImpl())).
                get(ListVisitsFragmentViewModel.class);
        setupToolbar();
        setupViews();
        observeVisits();
    }

    private void observeVisits() {
        viewModel.getVisits().observe(this, new Observer<List<Visit>>() {
            @Override
            public void onChanged(List<Visit> visits) {
                listAdapter.submitList(visits);
                b.lblEmptyView.setVisibility(visits.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }

    private void setupViews() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        listAdapter = new ListVisitsFragmentAdapter();

        b.lstVisits.setHasFixedSize(true);
        b.lstVisits.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstVisit_columns)));
        b.lstVisits.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        b.lstVisits.setAdapter(listAdapter);
    }

}
