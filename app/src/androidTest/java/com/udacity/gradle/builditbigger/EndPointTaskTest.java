package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by surajkumarsau on 19/08/16.
 */

public class EndPointTaskTest extends InstrumentationTestCase {

    private CountDownLatch mSignal = null;
    private String mResponse;

    @Override
    protected void setUp() throws Exception {
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        mSignal.countDown();
    }

    @MediumTest
    public void testEndPointAsyncTask() throws Throwable {
        final EndpointAsyncTask task = new EndpointAsyncTask(new EndpointAsyncTask.ResponseListener() {
            @Override
            public void onResponseReceived(String response) {
                mSignal.countDown();
                mResponse = response;
            }
        });

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        });

        mSignal.await();

        assertNotNull(mResponse);
    }

}
