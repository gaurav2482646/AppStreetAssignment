package com.assignment.github.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.assignment.github.model.pojo.Project;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeViewModelTest {
    MockWebServer server = new MockWebServer();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getProjectsSuccess() {

        HomeViewModel viewModel = new HomeViewModel();
        viewModel.getProject();
        MutableLiveData<List<Project>> mutableLiveData = viewModel.getProjects();
        assertTrue(mutableLiveData.getValue().size() > 0);

    }

    @Test
    public void getProjectsFail() {
        HomeViewModel viewModel = new HomeViewModel();
        viewModel.getProject();
        MutableLiveData<List<Project>> mutableLiveData = viewModel.getProjects();
        assertEquals(0, mutableLiveData.getValue().size());

    }
}