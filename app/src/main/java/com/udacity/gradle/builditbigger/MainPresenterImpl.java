package com.udacity.gradle.builditbigger;

/**
 * Created by surajkumarsau on 12/08/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;

    public MainPresenterImpl(MainView view) {
        mView = view;
    }

    @Override
    public void requestJoke() {
        mView.showLoader();
        mView.callJokesAsyncTask();
    }

    @Override
    public void onResponseReceived(String response) {
        mView.hideLoader();
        mView.onJokeReceived(response);
    }
}
