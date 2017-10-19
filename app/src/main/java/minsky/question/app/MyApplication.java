package minsky.question.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import minsky.question.app.di.AppComponent;
import minsky.question.app.di.DaggerAppComponent;


public class MyApplication extends DaggerApplication {


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
