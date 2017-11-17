package minsky.question.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import minsky.question.app.di.ActivityBindingModule;
import minsky.question.app.di.AppComponent;
import minsky.question.app.di.AppModule;
import minsky.question.app.di.NetworkApiModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkApiModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface TestAppComponent extends AppComponent {

    void inject(TestMyApplication app);

    @Override
    void inject(DaggerApplication instance);

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        TestAppComponent.Builder application(Application application);

        TestAppComponent build();
    }
}
