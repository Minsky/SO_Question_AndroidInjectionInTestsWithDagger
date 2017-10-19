package minsky.question.app.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.support.v4.util.Preconditions.checkNotNull;

/*
    Uses google Volley lib for asynchronous network requests
 */
@Singleton
public class VolleyNetwork implements NetworkApi {
    private static final String TAG = VolleyNetwork.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private Context context;

    @Inject
    public VolleyNetwork(@NonNull Context context) {
        checkNotNull(context);

        this.context = context;
    }

    RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap
            // We use HttpURLConnection as the HTTP client.
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);

            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    private <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    @Override
    public void doRequestForData(@NonNull final MySampleRequestCallback requestCallback) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    requestCallback.onResponseReceived(response);

                } else {
                    requestCallback.onNetworkError();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work! Error:" + error.getMessage() + "\n" +
                        (error.networkResponse != null ? new String(error.networkResponse.data) + "\n" +
                                error.networkResponse.headers.toString() : "null data"));
                Log.d(TAG, "done with error");
                requestCallback.onNetworkError();
            }
        };

        StringRequest categoryRequest = new StringRequest(Request.Method.GET, "http://www.google.com", responseListener, errorListener);
        addToRequestQueue(categoryRequest);
    }
}
