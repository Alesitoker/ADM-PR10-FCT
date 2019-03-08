package com.iessaladillo.alejandro.adm_pr10_fct.base;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment {

    private static final String ARG_YEAR = "ARG_YEAR";
    private static final String ARG_MONTH = "ARG_MONTH";
    private static final String ARG_DAY = "ARG_DAY";

    private OnDateSetListener listener;
    private int year;
    private int month;
    private int day;

    public static DatePickerDialogFragment newInstance(Fragment fragment, int requestCode) {
        DatePickerDialogFragment frg = new DatePickerDialogFragment();
        frg.setTargetFragment(fragment, requestCode);
        return frg;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Default: today.
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        obtainArguments();
    }

    private void obtainArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            year = arguments.getInt(ARG_YEAR);
            month = arguments.getInt(ARG_MONTH);
            day = arguments.getInt(ARG_DAY);
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(requireActivity(), listener, year, month, day);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            if (getTargetFragment() != null) {
                listener = (OnDateSetListener) getTargetFragment();
            } else {
                listener = (OnDateSetListener) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("Listener must implement DatePickerDialogFragment.OnDateSetListener");
        }
    }

}