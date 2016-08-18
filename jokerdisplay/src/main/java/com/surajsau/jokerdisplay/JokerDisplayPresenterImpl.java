package com.surajsau.jokerdisplay;

/**
 * Created by surajkumarsau on 12/08/16.
 */
public class JokerDisplayPresenterImpl implements JokerDisplayPresenter {

    private JokerDisplayView mView;

    public JokerDisplayPresenterImpl(JokerDisplayView view) {
        mView = view;
    }

    @Override
    public void showJokeOnTextView(String joke) {
        mView.showJoke(joke);
    }
}
