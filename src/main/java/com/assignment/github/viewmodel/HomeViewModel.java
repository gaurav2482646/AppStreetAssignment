package com.assignment.github.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.assignment.github.MainApp;
import com.assignment.github.model.pojo.Project;
import com.assignment.github.model.retrofit.API;
import com.assignment.github.repository.BaseRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    public LiveData<API.Status> statusLiveData;
    public LiveData<List<Project>> projectLiveData;
    @Inject
    public BaseRepository baseRepository;
    private MainApp mainApp;

    public HomeViewModel() {
        mainApp = new MainApp();
        mainApp.getAppComponent().inject(this);
        this.projectLiveData = getProjects();
        this.statusLiveData = baseRepository.getStatus();
    }

    public MutableLiveData<List<Project>> getProjects() {
        return baseRepository.getProjects();
    }

    public void getProject() {
        baseRepository.getProject();

    }
}
