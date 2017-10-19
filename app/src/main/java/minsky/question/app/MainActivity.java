package minsky.question.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import minsky.question.app.network.NetworkApi;

public class MainActivity extends AppCompatActivity {

    private TextView responseView;
    private boolean progressBarVisible;

    @Inject
    NetworkApi networkApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = (TextView) findViewById(R.id.text);
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressbar();
        networkApi.doRequestForData(new NetworkApi.MySampleRequestCallback() {
            @Override
            public void onResponseReceived(String response) {
                responseView.setText(response);
                hideProgressbar();
            }

            @Override
            public void onNetworkError() {
                responseView.setText("Network Error!");
                hideProgressbar();
            }
        });
    }

    private void hideProgressbar() {
        progressBarVisible = false;
    }

    private void showProgressbar() {
        progressBarVisible = true;
    }
}
