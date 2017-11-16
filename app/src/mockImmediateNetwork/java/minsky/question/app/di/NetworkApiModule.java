package minsky.question.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import minsky.question.app.network.NetworkApi;

@Module
public class NetworkApiModule {
    @Provides
    static NetworkApi provideNetworkApi(Context context) {
        return new ImmediateResponseNetwork();
    }
}
