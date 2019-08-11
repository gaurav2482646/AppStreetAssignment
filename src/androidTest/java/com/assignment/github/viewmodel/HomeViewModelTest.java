package com.assignment.github.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.assignment.github.model.pojo.Project;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HomeViewModelTest {

    @Test
    public void getAllassests_Success_Test() {
        HomeViewModel viewModel = new HomeViewModel();
        viewModel.getProject();
        MutableLiveData<List<Project>> mutableLiveData = viewModel.getProjects();
        assertTrue(mutableLiveData.getValue().size() > 0);

    }

    @Test
    public void getAllassests_Fail_Test() {
        HomeViewModel viewModel = new HomeViewModel();
        viewModel.getProject();
        MutableLiveData<List<Project>> mutableLiveData = viewModel.getProjects();
        assertEquals(0, mutableLiveData.getValue().size());

    }

}