package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.surajsau.jokerdisplay.IContants;
import com.surajsau.jokerdisplay.JokerDisplayActivity;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.MainPresenter;
import com.udacity.gradle.builditbigger.MainPresenterImpl;
import com.udacity.gradle.builditbigger.MainView;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainView, View.OnClickListener, EndpointAsyncTask.ResponseListener {

    private Button btnShowJoke;
    private ProgressBar mProgress;

    private MainPresenter mPresenter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initResources(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initResources(View view) {
        mPresenter = new MainPresenterImpl(this);

        btnShowJoke = (Button) view.findViewById(R.id.btnShowJoke);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
        btnShowJoke.setOnClickListener(this);
    }

    @Override
    public void showLoader() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onJokeReceived(String joke) {
        Intent startJokesDisplayIntent = new Intent(getActivity(), JokerDisplayActivity.class);
        startJokesDisplayIntent.putExtra(IContants.JOKE_STRING, joke);
        startActivity(startJokesDisplayIntent);
    }

    @Override
    public void callJokesAsyncTask() {
        new EndpointAsyncTask(this).execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShowJoke: {
                mPresenter.requestJoke();
            }
        }
    }

    @Override
    public void onResponseReceived(String response) {
        mPresenter.onResponseReceived(response);
    }
}
