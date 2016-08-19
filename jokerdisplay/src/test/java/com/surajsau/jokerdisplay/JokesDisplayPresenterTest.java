package com.surajsau.jokerdisplay;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by surajkumarsau on 18/08/16.
 */
public class JokesDisplayPresenterTest {

    private JokerDisplayPresenter mPresenter;
    private JokerDisplayView mView;

    @Before
    public void setup() {
        mView = mock(JokerDisplayView.class);
        mPresenter = new JokerDisplayPresenterImpl(mView);
    }

    @Test
    public void testViewShowsJokeOnAskingToShowJokeToPresenter() {
        mPresenter.showJokeOnTextView("Sample Joke");
        verify(mView).showJoke("Sample Joke");
    }

}
