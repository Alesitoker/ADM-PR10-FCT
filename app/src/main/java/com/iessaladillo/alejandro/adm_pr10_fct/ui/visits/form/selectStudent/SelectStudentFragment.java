package com.iessaladillo.alejandro.adm_pr10_fct.ui.visits.form.selectStudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentListStudentsBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentSelectStudentBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.student.list.ListStudentsFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

public class SelectStudentFragment extends Fragment {

    private FragmentSelectStudentBinding b;
    private NavController navController;
    private ListStudentsFragmentAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentSelectStudentBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        listAdapter = new ListStudentsFragmentAdapter();
        listAdapter.setOnSelectItemClickListener(position -> sendStudent());

        b.lstStudent.setHasFixedSize(true);
        b.lstStudent.setLayoutManager(new GridLayoutManager(requireContext(), getResources().getInteger(R.integer.lstStudent_columns)));
        b.lstStudent.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        b.lstStudent.setAdapter(listAdapter);
    }

    private void sendStudent() {

    }
}
