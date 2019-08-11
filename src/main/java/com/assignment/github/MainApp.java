package com.assignment.github;

import android.app.Activity;
import android.app.Application;

import com.assignment.github.dagger.inject.AppComponent;
import com.assignment.github.dagger.inject.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MainApp extends Application implements HasActivityInjector {

    private static AppComponent component;

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .application(this)
                .build();
        component.inject(this);
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
