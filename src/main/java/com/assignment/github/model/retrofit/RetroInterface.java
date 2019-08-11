package com.assignment.github.model.retrofit;

import com.assignment.github.model.pojo.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroInterface {

    @GET(API.PROJECTS)
    Call<List<Project>> getProjects();

}
