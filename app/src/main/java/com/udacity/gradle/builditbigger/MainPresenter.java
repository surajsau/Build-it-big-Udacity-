package com.udacity.gradle.builditbigger;

/**
 * Created by surajkumarsau on 12/08/16.
 */
public interface MainPresenter {
    void requestJoke();
    void onResponseReceived(String joke);
}
