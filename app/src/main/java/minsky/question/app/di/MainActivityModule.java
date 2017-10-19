package minsky.question.app.di;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import minsky.question.app.MainActivity;

@Module
public abstract class MainActivityModule {
    @Binds
    @ActivityScoped
    abstract Activity activity(MainActivity mainActivity);
}
