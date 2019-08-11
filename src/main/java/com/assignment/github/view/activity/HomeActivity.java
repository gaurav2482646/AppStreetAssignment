package com.assignment.github.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.github.R;
import com.assignment.github.adapter.ProjectAdapter;
import com.assignment.github.model.pojo.Project;
import com.assignment.github.model.retrofit.Status;
import com.assignment.github.viewmodel.HomeViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Snackbar snackbar;
    private HomeViewModel viewModel;
    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;
    private CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        layout = findViewById(R.id.coordinatorLayout);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        observerProjects();
        observerStatus();
        viewModel.getProject();
        initAdapter();


    }

    void initAdapter() {
        projectAdapter = new ProjectAdapter(this);
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(projectAdapter);
    }

    void showSnackbar() {
        snackbar = Snackbar
                .make(layout, "Internet Not Working", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.getProject();
                        snackbar.dismiss();

                    }
                });
        snackbar.setActionTextColor(getResources().getColor(R.color.color_prepaid_test));
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();

    }

    private void observerStatus() {
        viewModel.statusLiveData.observe(this, new Observer<Status>() {
            @Override
            public void onChanged(@Nullable Status status) {
                if (status != null) {
                    switch (status) {
                        case LOADED:
                            Toast.makeText(HomeActivity.this, "Projects Loaded Success", Toast.LENGTH_SHORT).show();
                            recyclerView.setVisibility(View.VISIBLE);
                            break;
                        case FAILED:
                            showSnackbar();
                            Toast.makeText(HomeActivity.this, "Some Error Occurred Try Again", Toast.LENGTH_LONG).show();
                            recyclerView.setVisibility(View.GONE);
                            return;
                    }
                }
            }
        });
    }

    private void observerProjects() {
        viewModel.projectLiveData.observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> devAssets) {
                if (devAssets != null) {
                    projectAdapter.updateList(devAssets);
                }
            }
        });
    }

}
