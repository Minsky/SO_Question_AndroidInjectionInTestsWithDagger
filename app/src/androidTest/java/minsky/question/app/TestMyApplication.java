package minsky.question.app;

import android.support.test.espresso.core.deps.dagger.Component;

import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import minsky.question.app.di.AppComponent;
import minsky.question.app.di.DaggerAppComponent;

public class TestMyApplication extends MyApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}


