package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.surajsau.jokerdisplay.IContants;
import com.surajsau.jokerdisplay.JokerDisplayActivity;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainView, View.OnClickListener {

    private Button btnShowJoke;
    private AdView mAdView;
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
        buildAd();
    }

    private void buildAd() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void initResources(View view) {
        mPresenter = new MainPresenterImpl(this);

        mAdView = (AdView) view.findViewById(R.id.adView);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShowJoke: {
                mPresenter.requestJoke();
            }
        }
    }
}
