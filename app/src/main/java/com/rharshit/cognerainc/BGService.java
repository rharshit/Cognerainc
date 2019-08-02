package com.rharshit.cognerainc;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static com.rharshit.cognerainc.Callbacks.processCallback;
import static com.rharshit.cognerainc.Callbacks.setCallback;

public class BGService extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob: Started");
        processCallback = new Callbacks.processCallback() {
            @Override
            public void process(String s) {
                setCallback.set(processText(s));
            }
        };
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob: Stopped");
        processCallback = null;
        return true;
    }

    private String processText(String s) {
        return s + "Hello";
    }
}
