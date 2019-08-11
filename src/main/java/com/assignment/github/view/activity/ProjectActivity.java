package com.assignment.github.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.github.R;
import com.assignment.github.model.pojo.Project;
import com.bumptech.glide.Glide;

public class ProjectActivity extends AppCompatActivity implements View.OnClickListener {
    private Project project;
    private ImageView imageViewProfile;
    private TextView textViewUserName, textViewType, textViewURL, textViewProjectName, textViewDescription, textViewProjectURL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        initUi();
        project = getIntent().getExtras().getParcelable("project");
        setupInfo();
    }
    void initUi() {
        textViewUserName = (TextView) findViewById(R.id.username);
        textViewType = (TextView) findViewById(R.id.type);
        textViewURL = (TextView) findViewById(R.id.url);
        textViewProjectName = (TextView) findViewById(R.id.repoName);
        textViewDescription = (TextView) findViewById(R.id.description);
        textViewProjectURL = (TextView) findViewById(R.id.projectURL);
        imageViewProfile = (ImageView) findViewById(R.id.avatar);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    private void setupInfo() {
        if (project != null) {
            Glide.with(this).load(project.getAvatar()).into(imageViewProfile);
            textViewUserName.setText(project.getUsername());
            textViewType.setText(project.getType());
            textViewProjectName.setText(project.getRepo().getName());
            textViewDescription.setText(project.getRepo().getDescription());
            textViewProjectURL.setText(project.getRepo().getUrl());
            textViewURL.setText(project.getUrl());
            textViewURL.setOnClickListener(this);
            textViewProjectURL.setOnClickListener(this);
       }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.projectURL:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(project.getRepo().getUrl()));
                startActivity(intent1);
                return;
            case R.id.url:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(project.getUrl()));
                startActivity(intent);
                return;


        }
    }


    }

