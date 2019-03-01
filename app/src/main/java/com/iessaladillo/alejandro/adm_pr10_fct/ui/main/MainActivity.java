package com.iessaladillo.alejandro.adm_pr10_fct.ui.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.iessaladillo.alejandro.adm_pr10_fct.R;

public class MainActivity extends AppCompatActivity implements ToolbarConfigurationInterface {

    private NavController navController;
    AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private static final String PREFERENCES_FILE = "prefs";
    private static final String PREF_NAV_DRAWER_OPENED = "navdrawerOpened";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.navHostFragment);
        setupViews();
    }

    private void setupViews() {
        drawerLayout = ActivityCompat.requireViewById(this, R.id.drawerLayout);
        NavigationView navigationView = ActivityCompat.requireViewById(this, R.id.navigationView);
        CircleImageView imgProfile = ViewCompat.requireViewById(navigationView.getHeaderView(0), R.id.imgProfile);

        appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.alumnosFragment, R.id.empresasFragment, R.id.proximasVisitasFragment,
                        R.id.visitasFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();

    }

    private void setupNavigationDrawer() {
        NavigationView navigationView =
                ActivityCompat.requireViewById(this, R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
        if (!readPreference()) {
            drawerLayout.openDrawer(GravityCompat.START);
            savePreference();
        }
    }

    private boolean readPreference() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(PREF_NAV_DRAWER_OPENED, false);
    }

    private void savePreference() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PREF_NAV_DRAWER_OPENED, true);
        editor.apply();
    }

    @Override
    public void configureToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(navController.getCurrentDestination().getLabel());
        setupNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
