package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by surajkumarsau on 19/08/16.
 */
@RunWith(AndroidJUnit4.class)
public class EndPointTaskTest extends ApplicationTestCase<Application>{

    private CountDownLatch mSignal = null;
    private String mResponse;

    public EndPointTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        mSignal.countDown();
    }

    @Test
    public void testEndPointAsyncTask() throws InterruptedException {
        EndpointAsyncTask task = new EndpointAsyncTask(new EndpointAsyncTask.ResponseListener() {
            @Override
            public void onResponseReceived(String response) {
                mSignal.countDown();
                mResponse = response;
            }
        });

        task.execute();
        mSignal.await();

        assertEquals(mResponse, "When you try your best and you don't succeed...");
    }

}
