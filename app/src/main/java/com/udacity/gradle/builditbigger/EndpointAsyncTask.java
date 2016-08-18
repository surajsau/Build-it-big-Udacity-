package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import in.surajkumarsau.builditbig.backend.myApi.MyApi;

/**
 * Created by surajkumarsau on 16/08/16.
 */
public class EndpointAsyncTask extends AsyncTask<Void, Void, String> {

    private MyApi mApi;
    private ResponseListener mListener;

    public interface ResponseListener {
        void onResponseReceived(String response);
    }

    public EndpointAsyncTask(ResponseListener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(mApi == null) {
            mApi = setupGCEService().build();
        }

        try {
            return mApi.getJoke().execute().getData();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(mListener != null)
            mListener.onResponseReceived(s);
    }

    private MyApi.Builder setupGCEService() {
        return new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://build-it-big-140511.appspot.com/_ah/api")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
    }
}
