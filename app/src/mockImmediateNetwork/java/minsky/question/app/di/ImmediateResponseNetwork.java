package minsky.question.app.di;

import minsky.question.app.network.NetworkApi;


// // TODO: 14.11.2017 Add Code which allows setting up the response
// // TODO: 14.11.2017  For instance providing a asset file or something, like that.
public class ImmediateResponseNetwork implements NetworkApi
{
    public static final String EMPTY_PAGE = "Empty Page";

    private String response;
    private int countNetworkRequestCalls;

    public ImmediateResponseNetwork()
    {
        response = null;
        countNetworkRequestCalls = 0;
    }

    public void setResponse(String response)
    {
        this.response = response;
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
        requestCallback.onResponseReceived("Empty Page");
    }
}
