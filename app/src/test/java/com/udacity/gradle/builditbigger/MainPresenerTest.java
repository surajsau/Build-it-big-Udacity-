package com.udacity.gradle.builditbigger;

import com.udacity.gradle.builditbigger.MainPresenter;
import com.udacity.gradle.builditbigger.MainPresenterImpl;
import com.udacity.gradle.builditbigger.MainView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by surajkumarsau on 18/08/16.
 */
public class MainPresenerTest {

    private MainPresenter mPresenter;
    private MainView mView;

    @Before
    public void setup() {
        mView = mock(MainView.class);
        mPresenter = new MainPresenterImpl(mView);
    }

    @Test
    public void testOnRequestJokeShouldRequestJokeInAsyncTaskAndShowLoaderInView() {
        mPresenter.requestJoke();

        verify(mView).showLoader();
        verify(mView).callJokesAsyncTask();
    }

    @Test
    public void testOnResponseReceivedHideLoaderInViewAndStartJokesDisplayActivity() {
        mPresenter.onResponseReceived("Sample joke");

        verify(mView).hideLoader();
        verify(mView).onJokeReceived("Sample joke");
    }
}
