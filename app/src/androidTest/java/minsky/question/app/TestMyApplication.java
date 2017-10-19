package minsky.question.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import minsky.question.app.di.AppComponent;
import minsky.question.app.di.DaggerAppComponent;

public class TestMyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        TestAppComponent appComponent = DaggerTestAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}


