package com.udacity.gradle.builditbigger;

import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by surajkumarsau on 19/08/16.
 */
public class EndPointTaskTest extends InstrumentationTestCase {

    private boolean called = false;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        called = false;
    }

    @Test
    public void testEndPointAsyncTask() throws Throwable {
        CountDownLatch signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                new EndpointAsyncTask(new EndpointAsyncTask.ResponseListener() {
                    @Override
                    public void onResponseReceived(String response) {
                        called = (response != null);
                    }
                }).execute();
            }
        });

        signal.await(60, TimeUnit.SECONDS);
        assert(called);
    }
}
