package com.udacity.gradle.builditbigger;

/**
 * Created by surajkumarsau on 12/08/16.
 */
public interface MainView {
    void showLoader();
    void hideLoader();
    void onJokeReceived(String joke);
    void callJokesAsyncTask();
}
