package minsky.question.app;

import android.content.Context;
import android.support.test.espresso.core.deps.dagger.Module;

import dagger.Provides;
import minsky.question.app.di.NetworkApiModule;
import minsky.question.app.network.NetworkApi;
import minsky.question.app.network.VolleyNetwork;


@Module
public class TestNetworkApiModule extends NetworkApiModule {
    @Provides
    static NetworkApi provideNetworkApi(Context context) {
        return new ImmediateResponseNetwork();
    }
}
