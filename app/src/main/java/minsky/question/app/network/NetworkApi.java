package minsky.question.app.network;


public interface NetworkApi {
    void doRequestForData(MySampleRequestCallback requestCallback);

    interface MySampleRequestCallback {
        void onResponseReceived(String response);

        void onNetworkError();
    }
}
