package com.iessaladillo.alejandro.adm_pr10_fct.ui.about;

import android.os.Bundle;

import com.iessaladillo.alejandro.adm_pr10_fct.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

public class AboutMeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        setupViews();
        setupToolbar();
    }

    private void setupViews() {
        toolbar = ActivityCompat.requireViewById(this, R.id.toolbar);

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.aboutMeActivity_title));
    }
}
