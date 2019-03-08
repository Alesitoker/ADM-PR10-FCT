package com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.data.RepositoryImpl;
import com.iessaladillo.alejandro.adm_pr10_fct.data.local.model.StudentCompany;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentListStudentsBinding;
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

public class ListStudentsFragment extends Fragment {

    private FragmentListStudentsBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;
    private ListStudentsFragmentViewModel viewModel;
    private ListStudentsFragmentAdapter listAdapter;
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
        b = FragmentListStudentsBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, new ListStudentsFragmentViewModelFactory(
                Injector.provideRepository(requireContext()))).get(ListStudentsFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupViews();
        observeStudents();
        KeyboardUtils.hideSoftKeyboard(requireActivity());
    }

    private void observeStudents() {
        viewModel.getStudents().observe(this, student -> {
            listAdapter.submitList(student);
            b.lblEmptyView.setVisibility(student.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }

    private void setupViews() {
        setupRecyclerView();

        b.fabAdd.setOnClickListener(v -> navigateToAddStudent());
        b.lblEmptyView.setOnClickListener(v -> navigateToAddStudent());

    }

    private void setupRecyclerView() {
        listAdapter = new ListStudentsFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(position -> NavigateToEditStudent(listAdapter.getItem(position)));

        b.lstStudent.setHasFixedSize(true);
        b.lstStudent.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstStudent_columns)));
        b.lstStudent.setAdapter(listAdapter);
    }

    private void NavigateToEditStudent(StudentCompany student) {
        ListStudentsFragmentDirections.ActionStudentsToFormStudent action =
                ListStudentsFragmentDirections.actionStudentsToFormStudent().setId(student.getId());
        navController.navigate(action);
    }

    private void navigateToAddStudent() {
        ListStudentsFragmentDirections.ActionStudentsToFormStudent action =
                ListStudentsFragmentDirections.actionStudentsToFormStudent().setId(0L);
        navController.navigate(action);
    }
}
