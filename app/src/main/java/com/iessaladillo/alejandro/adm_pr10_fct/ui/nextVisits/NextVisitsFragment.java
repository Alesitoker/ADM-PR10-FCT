package com.iessaladillo.alejandro.adm_pr10_fct.ui.nextVisits;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.VisitStudent;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentNextvisitsBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.di.Injector;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.ToolbarConfigurationInterface;
import com.iessaladillo.alejandro.adm_pr10_fct.utils.KeyboardUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;

public class NextVisitsFragment extends Fragment {

    private FragmentNextvisitsBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;
    private NextVisitsFragmentAdapter listAdapter;
    private NextVisitsFragmentViewModel viewModel;
    private SharedPreferences settings;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            toolbarConfiguration = (ToolbarConfigurationInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Listener must implement ToolbarConfigurationInterface");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        settings.unregisterOnSharedPreferenceChangeListener(onSharePreferencesChangeListener);
    }

    @Override
    public void onResume() {
        super.onResume();
//        settings.registerOnSharedPreferenceChangeListener(onSharePreferencesChangeListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentNextvisitsBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new NextVisitsFragmentViewModelFactory(
                Injector.provideRepository(requireContext().getApplicationContext()))).get(NextVisitsFragmentViewModel.class);
        settings = PreferenceManager.getDefaultSharedPreferences(requireContext());
        setupToolbar();
        setupRecyclerView();
        observeNextVisits();
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void observeNextVisits() {
        viewModel.getVisits().observe(this, visits -> {
            listAdapter.submitList(visits);
        });
    }

    private void setupRecyclerView() {
        listAdapter = new NextVisitsFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(
                position -> NavigateToEditVisit(listAdapter.getItem(position)));
        listAdapter.setDays(settings.getInt(getString(R.string.prefDaysVisits_key), getResources().getInteger(R.integer.prefDaysVisits_defaultValue)));

        b.lstNextVisits.setHasFixedSize(true);
        b.lstNextVisits.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstVisit_columns)));
        b.lstNextVisits.setAdapter(listAdapter);
    }

    private void NavigateToEditVisit(VisitStudent visit) {

    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }
}
