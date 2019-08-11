package com.assignment.github.repository;

import android.arch.lifecycle.MutableLiveData;

import com.assignment.github.model.pojo.Project;
import com.assignment.github.model.retrofit.RetroInterface;
import com.assignment.github.model.retrofit.Status;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class BaseRepository {

    private MutableLiveData<List<Project>> projectLiveData = new MutableLiveData<>();
    private MutableLiveData<Status> statusLiveData = new MutableLiveData<>();
    private RetroInterface mRetroInterface;

    @Inject
    public BaseRepository(RetroInterface retroInterface) {
        this.mRetroInterface = retroInterface;
    }

    public void getProject() {
        statusLiveData.setValue(Status.PROCESSING);
        mRetroInterface.getProjects().enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                statusLiveData.setValue(Status.LOADED);
                projectLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                statusLiveData.setValue(Status.FAILED);
            }
        });
    }

    public MutableLiveData<Status> getStatus() {
        return statusLiveData;
    }

    public MutableLiveData<List<Project>> getProjects() {
        return projectLiveData;
    }


}
