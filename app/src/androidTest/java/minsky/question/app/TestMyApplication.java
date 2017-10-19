package minsky.question.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class TestMyApplication extends MyApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        TestAppComponent appComponent = DaggerTestAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}


