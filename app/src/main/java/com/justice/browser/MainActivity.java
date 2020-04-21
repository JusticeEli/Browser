package com.justice.browser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int READ_WRITE_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private MainActivityAdapter mainActivityAdapter;
    private static List<WebSite> list;
    private EditText searchEdtTxt;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigationDrawer();
        askForPermissions();
        initWidgets();
        setOnClickListeners();




    }

    private void initNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void askForPermissions() {
        String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (!((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) == PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED))) {
            ActivityCompat.requestPermissions(this, permissions, READ_WRITE_REQUEST_CODE);

        }
    }

    private void setOnClickListeners() {
        searchEdtTxt.addTextChangedListener(new TextWatcher() {
            List<WebSite> list = new ArrayList<>();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (searchEdtTxt.getText().toString().trim().isEmpty()) {
                    if (ApplicationClass.choosenWebsitesList == null) {
                        return;
                    }

                    mainActivityAdapter.setList(ApplicationClass.choosenWebsitesList);
                    return;
                }
                list.clear();
                if (ApplicationClass.choosenWebsitesList == null) {
                    return;
                }
                for (WebSite webSite : ApplicationClass.choosenWebsitesList) {
                    if (webSite.getName().contains(searchEdtTxt.getText().toString().toLowerCase())) {
                        if (!list.contains(webSite)) {
                            list.add(webSite);
                        }
                    }
                }
                mainActivityAdapter.setList(list);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, ChooseWebsiteActivity.class));

                break;
            case R.id.chooseMenu:
                startActivity(new Intent(this, ChooseWebsiteActivity.class));

                break;
            case R.id.exitMenu:
                finish();
                break;
        }
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void initWidgets() {
        searchEdtTxt = findViewById(R.id.searchEdtTxt);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mainActivityAdapter = new MainActivityAdapter(this, ApplicationClass.choosenWebsitesList);
        recyclerView.setAdapter(mainActivityAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, ChooseWebsiteActivity.class));

                break;
            case R.id.chooseMenu:
                startActivity(new Intent(this, ChooseWebsiteActivity.class));

                break;
            case R.id.exitMenu:
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
