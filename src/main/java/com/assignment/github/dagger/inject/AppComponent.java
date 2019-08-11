package com.assignment.github.dagger.inject;

import android.app.Application;

import com.assignment.github.MainApp;
import com.assignment.github.viewmodel.HomeViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {DaggerModule.class,
        AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(HomeViewModel mainViewModel);
    void inject(MainApp mainApp);
}
