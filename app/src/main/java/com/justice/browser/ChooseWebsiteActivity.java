package com.justice.browser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChooseWebsiteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private EditText searchEdtTxt;
    private ChooseWebsiteRecyclerAdapter chooseWebsiteRecyclerAdapter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_website);
        initNavigationDrawer();

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }


        switch (item.getItemId()) {
           case R.id.homeMenu:
                startActivity(new Intent(this, MainActivity.class));
                finish();

                break;
            case R.id.exitMenu:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
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
                    if (ApplicationClass.allWebsitesList == null) {
                        return;
                    }

                    chooseWebsiteRecyclerAdapter.setList(ApplicationClass.allWebsitesList);
                    return;
                }
                list.clear();
                if (ApplicationClass.allWebsitesList == null) {
                    return;
                }

                for (WebSite webSite : ApplicationClass.allWebsitesList) {
                    if (webSite.getName().contains(searchEdtTxt.getText().toString().toLowerCase())) {
                        if (!list.contains(webSite)) {
                            list.add(webSite);
                        }
                    }
                }
                chooseWebsiteRecyclerAdapter.setList(list);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initWidgets() {
        recyclerView = findViewById(R.id.recyclerView);
        searchEdtTxt = findViewById(R.id.searchEdtTxt);
        chooseWebsiteRecyclerAdapter = new ChooseWebsiteRecyclerAdapter(ApplicationClass.allWebsitesList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chooseWebsiteRecyclerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.homeMenu:
                onBackPressed();
                break;


        }


        return true;
    }
}
