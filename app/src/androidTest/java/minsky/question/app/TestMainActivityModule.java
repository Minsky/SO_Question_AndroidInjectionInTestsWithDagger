package minsky.question.app;

import android.app.Application;

import javax.inject.Singleton;
import javax.sql.DataSource;

import dagger.Provides;
import minsky.question.app.network.NetworkApi;

public class TestMainActivityModule {

    private final TestMyApplication application;

    public TestMainActivityModule(TestMyApplication application) {
        this.application = application;
    }
}
