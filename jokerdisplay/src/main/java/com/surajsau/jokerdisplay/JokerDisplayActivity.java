package com.surajsau.jokerdisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class JokerDisplayActivity extends AppCompatActivity implements JokerDisplayView {

    private TextView tvJoke;

    private String joke;

    private JokerDisplayPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker_display);

        getDataFromBundle();
        initResources();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.showJokeOnTextView(joke);
    }

    private void initResources() {
        tvJoke = (TextView) findViewById(R.id.tvJoke);

        mPresenter = new JokerDisplayPresenterImpl(this);
    }

    @Override
    public void showJoke(String joke) {
        tvJoke.setText(joke);
    }

    private void getDataFromBundle() {
        if(getIntent() != null) {
            joke = getIntent().getStringExtra(IContants.JOKE_STRING);
        }
    }
}
