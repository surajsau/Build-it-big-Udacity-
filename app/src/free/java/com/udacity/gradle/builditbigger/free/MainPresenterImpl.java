package com.udacity.gradle.builditbigger.free;

import com.udacity.gradle.builditbigger.EndpointAsyncTask;

/**
 * Created by surajkumarsau on 12/08/16.
 */
public class MainPresenterImpl implements MainPresenter, EndpointAsyncTask.ResponseListener {

    private MainView mView;

    public MainPresenterImpl(MainView view) {
        mView = view;
    }

    @Override
    public void requestJoke() {
        mView.showLoader();
        new EndpointAsyncTask(this).execute();
    }

    @Override
    public void onResponseReceived(String response) {
        mView.hideLoader();
        mView.onJokeReceived(response);
    }
}
