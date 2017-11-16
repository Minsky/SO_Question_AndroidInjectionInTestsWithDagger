package minsky.question.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import minsky.question.app.di.*;
import minsky.question.app.network.NetworkApi;

@Module
public class TestNetworkApiModule extends NetworkApiModule {

    @Provides
    static NetworkApi provideNetworkApi(Context context) {
        return new UnresponsiveNetworkModule();
    }
}
