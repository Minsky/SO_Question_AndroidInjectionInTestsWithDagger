package minsky.question.app.di;

import minsky.question.app.network.NetworkApi;

public class UnresponsiveNetworkModule implements NetworkApi
{
    private int countNetworkRequestCalls;

    public UnresponsiveNetworkModule()
    {
        countNetworkRequestCalls = 0;
    }

    public boolean wasNetworkRequestCalled()
    {
        return countNetworkRequestCalls > 0;
    }

    public int countOfNetworkCalls()
    {
        return countNetworkRequestCalls;
    }

    @Override
    public void doRequestForData(MySampleRequestCallback requestCallback) {
        requestCallback.onNetworkError();
    }
}
