package com.iessaladillo.alejandro.adm_pr10_fct.ui.proximasVisitas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iessaladillo.alejandro.adm_pr10_fct.R;
import com.iessaladillo.alejandro.adm_pr10_fct.databinding.FragmentProximasvisitasBinding;
import com.iessaladillo.alejandro.adm_pr10_fct.ui.main.ToolbarConfigurationInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ProximasVisitasFragment extends Fragment {

    private FragmentProximasvisitasBinding b;
    private ToolbarConfigurationInterface toolbarConfiguration;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        toolbarConfiguration = (ToolbarConfigurationInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentProximasvisitasBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = b.toolbar;
        toolbarConfiguration.configureToolbar(toolbar);
    }
}
